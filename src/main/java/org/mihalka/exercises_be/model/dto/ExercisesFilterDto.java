package org.mihalka.exercises_be.model.dto;

import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;

@Data
public class ExercisesFilterDto {

    private Long exerciseId;
    private String exercisesName;
    private Integer exerciseDifficultyLevel;
    private Long bodyPartId;
    private Long accessoryId;
    private Boolean isFreeWeight;


}
