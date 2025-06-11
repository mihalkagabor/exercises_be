package org.mihalka.exercises_be.service;

import org.mihalka.exercises_be.model.dto.WorkoutCreationDto;
import org.mihalka.exercises_be.model.entity.WorkoutEntity;
import org.mihalka.exercises_be.repository.ExercisesRepository;
import org.mihalka.exercises_be.repository.UserDataRepository;
import org.mihalka.exercises_be.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
private final WorkoutRepository workoutRepository;
private final ExercisesRepository exercisesRepository;
private final UserDataRepository userDataRepository;

    public WorkoutService(WorkoutRepository workoutRepository, ExercisesRepository exercisesRepository, UserDataRepository userDataRepository) {
        this.workoutRepository = workoutRepository;
        this.exercisesRepository = exercisesRepository;
        this.userDataRepository = userDataRepository;
    }



    public WorkoutEntity createWorkout(WorkoutCreationDto workoutCreationDto){
        WorkoutEntity workoutEntity=new WorkoutEntity();
        workoutEntity.setExercise_amount(workoutCreationDto.getExercise_amount());
        workoutEntity.setWorkout_duration(workoutCreationDto.getWorkout_duration());
        workoutEntity.setStart_date(workoutCreationDto.getStart_date());
        workoutEntity.setEnd_date(workoutCreationDto.getEnd_date());
        workoutEntity.setExercise_weight(workoutCreationDto.getExercise_weight());
        workoutEntity.setExercises(
                exercisesRepository.findById(workoutCreationDto.getExercises_id())
                        .orElseThrow(()->new RuntimeException("Exercise not found"))
        );
        workoutEntity.setUserData(
                userDataRepository.findById(workoutCreationDto.getUserData_id())
                        .orElseThrow(()->new RuntimeException("User not found"))
        );
        return workoutRepository.save(workoutEntity);

    }
}
