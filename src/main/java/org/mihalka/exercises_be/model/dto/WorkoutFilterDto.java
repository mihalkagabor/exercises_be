package org.mihalka.exercises_be.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkoutFilterDto {

    private Long exerciseId;
    private Long bodyPartId;
    private LocalDate workoutDay;

}
