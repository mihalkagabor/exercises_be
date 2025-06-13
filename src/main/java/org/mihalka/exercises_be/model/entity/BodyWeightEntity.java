package org.mihalka.exercises_be.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mihalka.exercises_be.model.dto.BodyWeightCreationDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="body_weight")
@Setter
@NoArgsConstructor
public class BodyWeightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "body_weight_id")
    private Long bodyWeightId;

    @NotNull(message = "Provide your weight")
    @Column (name="weight",precision = 4,scale = 1)
    private BigDecimal weight;

    @Column(name="measure_date")
    private LocalDateTime measureDate;

    @ManyToOne
    @JoinColumn(name = "user_data_id")
    private UserDataEntity userData;

    public BodyWeightEntity(BodyWeightCreationDto dto){
        this.weight=dto.getWeight();
        this.measureDate =LocalDateTime.now();
    }

}
