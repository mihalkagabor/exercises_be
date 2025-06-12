package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.ExercisesCreationDto;
import org.mihalka.exercises_be.model.entity.ExercisesEntity;
import org.mihalka.exercises_be.repository.AccessoryRepository;
import org.mihalka.exercises_be.repository.DifficultyLevelRepository;
import org.mihalka.exercises_be.repository.ExercisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExercisesService {
    private final ExercisesRepository exercisesRepository;
    private final AccessoryRepository accessoryRepository;
    private final DifficultyLevelRepository difficultyLevelRepository;

    @Autowired
    public ExercisesService(ExercisesRepository exercisesRepository, AccessoryRepository accessoryRepository, DifficultyLevelRepository difficultyLevelRepository) {
        this.exercisesRepository = exercisesRepository;
        this.accessoryRepository = accessoryRepository;
        this.difficultyLevelRepository = difficultyLevelRepository;
    }

    public void createExercises(ExercisesCreationDto exercisesCreationDto){
        ExercisesEntity exercisesEntity=new ExercisesEntity(exercisesCreationDto);
        exercisesEntity.setAccessory(
                accessoryRepository.findById(exercisesCreationDto.getAccessory_id())
                        .orElseThrow(()-> new RuntimeException("Accessory not found"))
        );
       exercisesEntity.setDifficultyLevel(
               difficultyLevelRepository.findById(exercisesCreationDto.getDifficulty_id())
                       .orElseThrow(()-> new RuntimeException("Difficulty not found")
       ));

         exercisesRepository.save(exercisesEntity);
    }
}
