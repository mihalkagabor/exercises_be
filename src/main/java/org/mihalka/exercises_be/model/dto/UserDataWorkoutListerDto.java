package org.mihalka.exercises_be.model.dto;

import lombok.Data;
import org.mihalka.exercises_be.model.entity.ExercisesEntity;
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
        this.workout_id=workoutEntity.getWorkout_id();
        this.start_date=workoutEntity.getStart_date();
        this.end_date=workoutEntity.getEnd_date();
        this.workout_duration=workoutEntity.getWorkout_duration();
        this.exercise_amount=workoutEntity.getExercise_amount();
        this.exercise_weight=workoutEntity.getExercise_weight();
        this.exercises=workoutEntity.getExercises().getExercise_name().toString();
    }


}
