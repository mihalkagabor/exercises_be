package org.mihalka.exercises_be.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mihalka.exercises_be.model.entity.BodyWeightEntity;
import org.mihalka.exercises_be.model.enums.Sex;
import org.mihalka.exercises_be.model.enums.UserRole;

@Getter
@Setter
@NoArgsConstructor
public class UserDataCreationDto {

    @NotBlank(message = "You must provide a firs name")
    private String first_name;

    @NotBlank(message = "You must provide a last name")
    private String last_name;

    @NotNull(message = "You must provide your height")
    private Integer height;

    @NotNull(message = "You must provide your birth_year")
    private Integer birth_year;

    @NotNull(message = "You must provide your gender")
    private Sex sex;

    @NotNull()
    private UserRole user_role;


    private BodyWeightEntity bodyWeight;

}
