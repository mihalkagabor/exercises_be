package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.BodyPartCreationDto;
import org.mihalka.exercises_be.model.entity.BodyPartEntity;
import org.mihalka.exercises_be.repository.BodyPartRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BodyPartService {

    private final BodyPartRepository bodyPartRepository;

    public BodyPartService(BodyPartRepository bodyPartRepository) {
        this.bodyPartRepository = bodyPartRepository;
    }

    public void createBodyPart(BodyPartCreationDto bodyPartCreationDto){
        BodyPartEntity bodyPartEntity= new BodyPartEntity(bodyPartCreationDto);
        bodyPartRepository.save(bodyPartEntity);
    }




}
