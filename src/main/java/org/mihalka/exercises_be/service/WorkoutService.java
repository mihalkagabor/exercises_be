package org.mihalka.exercises_be.service;

import org.mihalka.exercises_be.model.dto.WorkoutCreationDto;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.model.entity.WorkoutEntity;
import org.mihalka.exercises_be.repository.ExercisesRepository;
import org.mihalka.exercises_be.repository.WorkoutRepository;
import org.springframework.stereotype.Service;


@Service
public class WorkoutService {
private final WorkoutRepository workoutRepository;
private final ExercisesRepository exercisesRepository;
private final CurrentUserService currentUserService;

    public WorkoutService(WorkoutRepository workoutRepository, ExercisesRepository exercisesRepository,  CurrentUserService currentUserService) {
        this.workoutRepository = workoutRepository;
        this.exercisesRepository = exercisesRepository;
        this.currentUserService = currentUserService;
    }

    public void createWorkout(WorkoutCreationDto workoutCreationDto){
        UserDataEntity userData = currentUserService.getCurrentUserData();

        WorkoutEntity workoutEntity=new WorkoutEntity();
        workoutEntity.setExercise_amount(workoutCreationDto.getExercise_amount());
        workoutEntity.setStart_date(workoutCreationDto.getStart_date());
        workoutEntity.setEnd_date(workoutCreationDto.getEnd_date());
        workoutEntity.setExercise_weight(workoutCreationDto.getExercise_weight());
        workoutEntity.setExercises(
                exercisesRepository.findById(workoutCreationDto.getExercises_id())
                        .orElseThrow(()->new RuntimeException("Exercise not found"))
        );
        workoutEntity.setUserData(userData);
         workoutRepository.save(workoutEntity);
    }

}
