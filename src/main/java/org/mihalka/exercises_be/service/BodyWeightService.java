package org.mihalka.exercises_be.service;

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
public class BodyWeightService {

    private BodyWeightRepository bodyWeightRepository;
    private final UserDataRepository userDataRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public BodyWeightService(BodyWeightRepository bodyWeightRepository, UserDataRepository userDataRepository, AppUserRepository appUserRepository){
        this.bodyWeightRepository=bodyWeightRepository;
        this.userDataRepository = userDataRepository;
        this.appUserRepository = appUserRepository;
    }
    public BodyWeightEntity saveBodyWeightEntity(BodyWeightEntity bodyWeightEntity) {
        return bodyWeightRepository.save(bodyWeightEntity);

    }

    public BodyWeightEntity createBodyWeight(BodyWeightCreationDto dto){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        AppUserEntity appUser = appUserRepository.findByEmail(username)
                .or(() -> appUserRepository.findByName(username))
                .orElseThrow(() -> {
                    System.out.println("User not found with identifier: " + username);
                    return new UsernameNotFoundException("User not found with identifier: " + username);
                });

                    UserDataEntity userData = userDataRepository.findById(appUser.getUser_id())
                            .orElseThrow(() -> new RuntimeException("User not found"));

                    BodyWeightEntity bodyWeight = new BodyWeightEntity(dto);
                    bodyWeight.setMeasure_date(LocalDateTime.now());
                    bodyWeight.setUserData(userData);
                    return bodyWeightRepository.save(bodyWeight);

                }

    }