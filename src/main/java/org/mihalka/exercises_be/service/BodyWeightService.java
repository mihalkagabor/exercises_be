package org.mihalka.exercises_be.service;

import org.mihalka.exercises_be.model.dto.BodyWeightCreationDto;
import org.mihalka.exercises_be.model.dto.WrapperUserData;
import org.mihalka.exercises_be.model.entity.BodyWeightEntity;
import org.mihalka.exercises_be.repository.BodyWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BodyWeightService {

    private BodyWeightRepository bodyWeightRepository;

    @Autowired
    public BodyWeightService(BodyWeightRepository bodyWeightRepository){
        this.bodyWeightRepository=bodyWeightRepository;
    }
    public BodyWeightEntity saveBodyWeightEntity(BodyWeightEntity bodyWeightEntity) {
        return bodyWeightRepository.save(bodyWeightEntity);

    }

    public BodyWeightEntity createBodyWeight(BodyWeightCreationDto dto){
        BodyWeightEntity bodyWeight=new BodyWeightEntity(dto);
        return bodyWeightRepository.save(bodyWeight);
    }
}
