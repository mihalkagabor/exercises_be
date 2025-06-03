package org.mihalka.exercises_be.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BodyPartCreationDto {

    @NotBlank(message = "You must provide a body part name")
    private String body_part_name;



}
