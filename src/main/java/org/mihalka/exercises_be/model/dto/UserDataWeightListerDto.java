package org.mihalka.exercises_be.model.dto;

import lombok.Data;
import org.mihalka.exercises_be.model.entity.BodyWeightEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserDataWeightListerDto {

    private Long body_weight_id;
    private BigDecimal weight;

    private LocalDateTime measure_date;

    public UserDataWeightListerDto(BodyWeightEntity bodyWeight){
        this.weight=bodyWeight.getWeight();
        this.measure_date=bodyWeight.getMeasureDate();
    }

}
