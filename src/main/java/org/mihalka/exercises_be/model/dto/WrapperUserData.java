package org.mihalka.exercises_be.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WrapperUserData {
    @Valid
    @NotNull
    private UserDataCreationDto userDataCreationDto;

    @Valid
    @NotNull
    private BodyWeightCreationDto bodyWeightCreationDto;
}
