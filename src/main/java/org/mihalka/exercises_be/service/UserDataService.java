package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.BodyWeightCreationDto;
import org.mihalka.exercises_be.model.dto.UserDataCreationDto;
import org.mihalka.exercises_be.model.dto.UserDataListerDto;
import org.mihalka.exercises_be.model.dto.UserDataWeightListerDto;
import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.model.entity.BodyWeightEntity;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.repository.AppUserRepository;
import org.mihalka.exercises_be.repository.BodyWeightRepository;
import org.mihalka.exercises_be.repository.UserDataRepository;
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

    @Autowired
    public UserDataService(UserDataRepository userDataRepository, BodyWeightService bodyWeightService, AppUserRepository appUserRepository, BodyWeightRepository bodyWeightRepository){
        this.userDataRepository=userDataRepository;
        this.bodyWeightService=bodyWeightService;
        this.appUserRepository = appUserRepository;
        this.bodyWeightRepository = bodyWeightRepository;
    }
//TODO Végpontot csinálni
public void createATotalUserData(UserDataCreationDto dto, BodyWeightCreationDto bodyWeightCreationDto){

    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
    String username=authentication.getName();

    AppUserEntity appUser = appUserRepository.findByEmail(username)
            .or(() -> appUserRepository.findByName(username))
            .orElseThrow(() -> {
                System.out.println("User not found with identifier: " + username);
                return new UsernameNotFoundException("User not found with identifier: " + username);
            });

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
    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
    String username=authentication.getName();

    AppUserEntity appUser = appUserRepository.findByEmail(username)
            .or(() -> appUserRepository.findByName(username))
            .orElseThrow(() -> {
                System.out.println("User not found with identifier: " + username);
                return new UsernameNotFoundException("User not found with identifier: " + username);
            });

    UserDataEntity userData=userDataRepository.findById(appUser.getUser_id())
            .orElseThrow(()-> new RuntimeException("User not found"));

    return bodyWeightRepository.findAllByUserData(userData).stream()
            .filter(bodyWeight -> bodyWeight.getUserData().getUser_data_id().equals(userData.getUser_data_id()))
            .sorted(Comparator.comparing(BodyWeightEntity::getMeasure_date))
            .map(UserDataWeightListerDto::new)
            .collect(Collectors.toList());

}
}
