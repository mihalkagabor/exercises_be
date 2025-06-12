package org.mihalka.exercises_be.model.dto;

import lombok.Data;

@Data
public class WorkoutFilterDto {

    private Long exerciseId;
    private Long bodyPartId;

}
