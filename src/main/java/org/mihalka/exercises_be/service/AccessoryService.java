package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.AccessoryCreationDto;
import org.mihalka.exercises_be.model.entity.AccessoryEntity;
import org.mihalka.exercises_be.repository.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class AccessoryService {

    private final AccessoryRepository accessoryRepository;

@Autowired
    public AccessoryService(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
    }

    public AccessoryEntity createAccessorry (AccessoryCreationDto accessoryCreationDto){
    AccessoryEntity accessory=new AccessoryEntity(accessoryCreationDto);
    return accessoryRepository.save(accessory);

    }
}
