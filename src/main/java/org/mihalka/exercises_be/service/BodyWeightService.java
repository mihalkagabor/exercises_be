package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.BodyWeightCreationDto;
import org.mihalka.exercises_be.model.dto.WrapperUserData;
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

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class BodyWeightService {

    private BodyWeightRepository bodyWeightRepository;
    private final UserDataRepository userDataRepository;
    private final AppUserRepository appUserRepository;
    private final  CurrentUserService currentUserService;

    @Autowired
    public BodyWeightService(BodyWeightRepository bodyWeightRepository, UserDataRepository userDataRepository, AppUserRepository appUserRepository, CurrentUserService currentUserService){
        this.bodyWeightRepository=bodyWeightRepository;
        this.userDataRepository = userDataRepository;
        this.appUserRepository = appUserRepository;
        this.currentUserService = currentUserService;
    }
    public BodyWeightEntity saveBodyWeightEntity(BodyWeightEntity bodyWeightEntity) {
        return bodyWeightRepository.save(bodyWeightEntity);

    }

    public BodyWeightEntity createBodyWeight(BodyWeightCreationDto dto){
                    UserDataEntity userData =currentUserService.getCurrentUserData();

                    BodyWeightEntity bodyWeight = new BodyWeightEntity(dto);
                    bodyWeight.setMeasure_date(LocalDateTime.now());
                    bodyWeight.setUserData(userData);
                    return bodyWeightRepository.save(bodyWeight);

                }

    }