package org.mihalka.exercises_be.service;

import org.mihalka.exercises_be.model.dto.BodyWeightCreationDto;
import org.mihalka.exercises_be.model.dto.UserDataCreationDto;
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

@Service
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final BodyWeightService bodyWeightService;
    private final AppUserRepository appUserRepository;

    @Autowired
    public UserDataService(UserDataRepository userDataRepository, BodyWeightService bodyWeightService, AppUserRepository appUserRepository){
        this.userDataRepository=userDataRepository;
        this.bodyWeightService=bodyWeightService;
        this.appUserRepository = appUserRepository;
    }

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

}
