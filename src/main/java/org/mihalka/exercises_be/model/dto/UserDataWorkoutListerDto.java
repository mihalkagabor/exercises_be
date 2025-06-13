package org.mihalka.exercises_be.model.dto;

import lombok.Data;
import org.mihalka.exercises_be.model.entity.WorkoutEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserDataWorkoutListerDto {

    private Long workout_id;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private Integer workout_duration;
    private Integer exercise_amount;
    private BigDecimal exercise_weight;
    private String exercises;

    public UserDataWorkoutListerDto(WorkoutEntity workoutEntity){
        this.workout_id=workoutEntity.getWorkoutId();
        this.start_date=workoutEntity.getStartDate();
        this.end_date=workoutEntity.getEndDate();
        this.workout_duration=workoutEntity.getWorkoutDuration();
        this.exercise_amount=workoutEntity.getExercisesAmount();
        this.exercise_weight=workoutEntity.getExercisesWeight();
        this.exercises=workoutEntity.getExercises().getExercisesName();
    }


}
