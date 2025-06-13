package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.BodyPartCreationDto;
import org.mihalka.exercises_be.model.entity.BodyPartEntity;
import org.mihalka.exercises_be.repository.BodyPartRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

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


    public List<BodyPartCreationDto> bodyPartLister() {
        return bodyPartRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(BodyPartEntity :: getBodyPartName))
                .map(BodyPartCreationDto :: new)
                .toList();
    }
}
