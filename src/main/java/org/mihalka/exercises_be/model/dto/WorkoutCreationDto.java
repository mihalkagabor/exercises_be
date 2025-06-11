package org.mihalka.exercises_be.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class WorkoutCreationDto {

    @NotNull()
    private Integer exercise_amount;

    @NotNull()
    private LocalDateTime start_date;

    @NotNull()
    private LocalDateTime end_date;

    @NotNull()
    private BigDecimal exercise_weight;

    @NotNull
    private Long exercises_id;


    private Long userData_id;


}
