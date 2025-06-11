package org.mihalka.exercises_be.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.mihalka.exercises_be.model.entity.AccessoryEntity;
import org.mihalka.exercises_be.model.entity.DifficultyLevelEntity;

@Getter
@Setter
@Data
public class ExercisesCreationDto {

    @NotBlank(message = "You must provide an exercise name")
    private String exercise_name;

    @NotNull(message = "You must provide is this exercise is free weight?")
    private Boolean is_free_weight;

    @NotNull(message = "You must provide a difficulty level")
    private Long difficulty_id;

    @NotNull(message = "You must provide a accessory name")
    private Long accessory_id;

}
