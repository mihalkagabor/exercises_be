package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.*;
import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.model.entity.BodyWeightEntity;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final BodyWeightService bodyWeightService;
    private final  CurrentUserService currentUserService;

    @Autowired
    public UserDataService(UserDataRepository userDataRepository, BodyWeightService bodyWeightService, CurrentUserService currentUserService){
        this.userDataRepository=userDataRepository;
        this.bodyWeightService=bodyWeightService;
        this.currentUserService = currentUserService;
    }
public void createATotalUserData(UserDataCreationDto dto, BodyWeightCreationDto bodyWeightCreationDto){
        AppUserEntity appUser = currentUserService.getCurrentAppUser();

    UserDataEntity userData=new UserDataEntity(dto,appUser);
    userData=userDataRepository.save(userData);
    BodyWeightEntity bodyWeightEntity=new BodyWeightEntity(bodyWeightCreationDto);
    bodyWeightEntity.setUserData(userData);
    bodyWeightService.saveBodyWeightEntity(bodyWeightEntity);
}

public List<UserDataListerDto> listUserData(){
        return userDataRepository.findAll().stream()
                .sorted(Comparator.comparing(UserDataEntity::getUserDataId))
                .map(UserDataListerDto::new)
                .collect(Collectors.toList());
}





}
