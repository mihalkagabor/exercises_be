package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.*;
import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.model.entity.BodyWeightEntity;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.model.entity.WorkoutEntity;
import org.mihalka.exercises_be.repository.AppUserRepository;
import org.mihalka.exercises_be.repository.BodyWeightRepository;
import org.mihalka.exercises_be.repository.UserDataRepository;
import org.mihalka.exercises_be.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final BodyWeightService bodyWeightService;
    private final AppUserRepository appUserRepository;
    private final BodyWeightRepository bodyWeightRepository;
private final WorkoutRepository workoutRepository;

    @Autowired
    public UserDataService(UserDataRepository userDataRepository, BodyWeightService bodyWeightService, AppUserRepository appUserRepository, BodyWeightRepository bodyWeightRepository, WorkoutRepository workoutRepository){
        this.userDataRepository=userDataRepository;
        this.bodyWeightService=bodyWeightService;
        this.appUserRepository = appUserRepository;
        this.bodyWeightRepository = bodyWeightRepository;
        this.workoutRepository = workoutRepository;
    }
public void createATotalUserData(UserDataCreationDto dto, BodyWeightCreationDto bodyWeightCreationDto){
        AppUserEntity appUser = getCurrentAppUser();

    UserDataEntity userData=new UserDataEntity(dto,appUser);
    userData=userDataRepository.save(userData);
    BodyWeightEntity bodyWeightEntity=new BodyWeightEntity(bodyWeightCreationDto);
    bodyWeightEntity.setUserData(userData);
    bodyWeightService.saveBodyWeightEntity(bodyWeightEntity);
}

public List<UserDataListerDto> listUserData(){
        return userDataRepository.findAll().stream()
                .sorted(Comparator.comparing(UserDataEntity::getUser_data_id))
                .map(UserDataListerDto::new)
                .collect(Collectors.toList());
}
//TODO Végpontot csinálni
public List<UserDataWeightListerDto> listUserDataWeight(){
    UserDataEntity userData=getCurrentUserData();

    return bodyWeightRepository.findAllByUserData(userData).stream()
            .sorted(Comparator.comparing(BodyWeightEntity::getMeasure_date))
            .map(UserDataWeightListerDto::new)
            .collect(Collectors.toList());
}
//TODO Végpontot csinálni

public List<UserDataWorkoutListerDto> listUserDataWorkout(){
    UserDataEntity userData=getCurrentUserData();

   return workoutRepository.findAllByUserData(userData).stream()
           .sorted(Comparator.comparing(WorkoutEntity::getStart_date).reversed())
           .map(UserDataWorkoutListerDto::new)
           .collect(Collectors.toList());

    }

    public AppUserEntity getCurrentAppUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        AppUserEntity appUser = appUserRepository.findByEmail(username)
                .or(() -> appUserRepository.findByName(username))
                .orElseThrow(() -> {
                    System.out.println("User not found with identifier: " + username);
                    return new UsernameNotFoundException("User not found with identifier: " + username);
                });
        return appUser;
    }

    public UserDataEntity getCurrentUserData(){
        AppUserEntity appUser = getCurrentAppUser();

        UserDataEntity userData=userDataRepository.findByAppUser(appUser)
                .orElseThrow(()->new RuntimeException("Cant find this user"));
        return userData;
    }

}
