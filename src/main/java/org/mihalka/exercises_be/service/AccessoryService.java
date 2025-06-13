package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.AccessoryCreationDto;
import org.mihalka.exercises_be.model.entity.AccessoryEntity;
import org.mihalka.exercises_be.repository.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Transactional
@Service
public class AccessoryService {

    private final AccessoryRepository accessoryRepository;

@Autowired
    public AccessoryService(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
    }

    public void createAccessory(AccessoryCreationDto accessoryCreationDto){
    AccessoryEntity accessory=new AccessoryEntity(accessoryCreationDto);
    accessoryRepository.save(accessory);

    }

    public List<AccessoryCreationDto> accessoryLister(){
    return  accessoryRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(AccessoryEntity::getAccessoryName))
                .map(AccessoryCreationDto:: new)
                .toList();
    }
}
