package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.ExercisesCreationDto;
import org.mihalka.exercises_be.model.dto.ExercisesFilterDto;
import org.mihalka.exercises_be.model.dto.ExercisesListDto;
import org.mihalka.exercises_be.model.entity.ExercisesEntity;
import org.mihalka.exercises_be.repository.AccessoryRepository;
import org.mihalka.exercises_be.repository.DifficultyLevelRepository;
import org.mihalka.exercises_be.repository.ExercisesRepository;
import org.mihalka.exercises_be.specification.ExercisesSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExercisesService {
    private final ExercisesRepository exercisesRepository;
    private final AccessoryRepository accessoryRepository;
    private final DifficultyLevelRepository difficultyLevelRepository;
    private final DifficultyLevelService difficultyLevelService;

    @Autowired
    public ExercisesService(ExercisesRepository exercisesRepository, AccessoryRepository accessoryRepository, DifficultyLevelRepository difficultyLevelRepository, DifficultyLevelService difficultyLevelService) {
        this.exercisesRepository = exercisesRepository;
        this.accessoryRepository = accessoryRepository;
        this.difficultyLevelRepository = difficultyLevelRepository;
        this.difficultyLevelService = difficultyLevelService;
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

    public List<ExercisesListDto> exercisesFilter(ExercisesFilterDto exercisesFilterDto){
        return exercisesRepository.findAll(ExercisesSpecification.exerciseFilter(exercisesFilterDto))
                .stream()
                .map(ExercisesListDto :: new)
                .collect(Collectors.toList());
    }
}
