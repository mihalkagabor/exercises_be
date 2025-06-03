package org.mihalka.exercises_be.service;

import org.mihalka.exercises_be.model.dto.BodyPartCreationDto;
import org.mihalka.exercises_be.model.entity.BodyPartEntity;
import org.mihalka.exercises_be.repository.BodyPartRepository;
import org.springframework.stereotype.Service;

@Service
public class BodyPartService {

    private final BodyPartRepository bodyPartRepository;

    public BodyPartService(BodyPartRepository bodyPartRepository) {
        this.bodyPartRepository = bodyPartRepository;
    }

    public BodyPartEntity createBodyPart(BodyPartCreationDto bodyPartCreationDto){
        BodyPartEntity bodyPartEntity= new BodyPartEntity(bodyPartCreationDto);
        return bodyPartRepository.save(bodyPartEntity);
    }


}
