package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.DifficultyLevelCreationDto;
import org.mihalka.exercises_be.model.entity.DifficultyLevelEntity;
import org.mihalka.exercises_be.repository.DifficultyLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class DifficultyLevelService {

    private final DifficultyLevelRepository difficultyLevelRepository;

@Autowired
    public DifficultyLevelService(DifficultyLevelRepository difficultyLevelRepository) {
        this.difficultyLevelRepository = difficultyLevelRepository;
    }

    public void createDifficultyLevel(DifficultyLevelCreationDto difficultyLevelCreationDto){
    DifficultyLevelEntity difficultyLevel=new DifficultyLevelEntity(difficultyLevelCreationDto);
     difficultyLevelRepository.save(difficultyLevel);
    }

    public List<DifficultyLevelCreationDto> listDifficultyLevels() {
    return difficultyLevelRepository.findAll()
            .stream()
            .sorted(Comparator.comparing(DifficultyLevelEntity::getDifficultyLevelLevel).reversed())
            .map(DifficultyLevelCreationDto::new)
            .toList();
    }
}
