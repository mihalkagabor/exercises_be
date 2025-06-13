package org.mihalka.exercises_be.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class WorkoutFilterDto {

    private Long exerciseId;
    private Long bodyPartId;
    private LocalDate workoutDay;
    private Integer workout_duration;
    private BigDecimal exercise_weight;
}
