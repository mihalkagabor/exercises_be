package org.mihalka.exercises_be.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DifficultyLevelCreationDto {

@NotNull(message = "You must provide a difficulty level")
private Integer difficulty_level_level;

@NotBlank(message = "You must provide a description")
private String difficulty_level_description;


}
