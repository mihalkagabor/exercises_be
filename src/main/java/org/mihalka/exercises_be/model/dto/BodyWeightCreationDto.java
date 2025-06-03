package org.mihalka.exercises_be.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mihalka.exercises_be.model.entity.UserDataEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BodyWeightCreationDto {

    @NotNull (message = "Muszáj megadni testsúlyt")
    private BigDecimal weight;

}
