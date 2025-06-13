package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.BodyWeightCreationDto;
import org.mihalka.exercises_be.model.dto.UserDataWeightListerDto;
import org.mihalka.exercises_be.model.entity.BodyWeightEntity;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.repository.BodyWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BodyWeightService {

    private final BodyWeightRepository bodyWeightRepository;
    private final  CurrentUserService currentUserService;

    @Autowired
    public BodyWeightService(BodyWeightRepository bodyWeightRepository, CurrentUserService currentUserService){
        this.bodyWeightRepository=bodyWeightRepository;
        this.currentUserService = currentUserService;
    }
    public void saveBodyWeightEntity(BodyWeightEntity bodyWeightEntity) {
         bodyWeightRepository.save(bodyWeightEntity);

    }

    public void createBodyWeight(BodyWeightCreationDto dto){
                    UserDataEntity userData =currentUserService.getCurrentUserData();

                    BodyWeightEntity bodyWeight = new BodyWeightEntity(dto);
                    bodyWeight.setMeasureDate(LocalDateTime.now());
                    bodyWeight.setUserData(userData);
                    bodyWeightRepository.save(bodyWeight);

                }

    public List<UserDataWeightListerDto> listUserDataWeight(){
        UserDataEntity userData=currentUserService.getCurrentUserData();

        return bodyWeightRepository.findAllByUserData(userData).stream()
                .sorted(Comparator.comparing(BodyWeightEntity::getMeasureDate).reversed())
                .map(UserDataWeightListerDto::new)
                .collect(Collectors.toList());
    }
    }