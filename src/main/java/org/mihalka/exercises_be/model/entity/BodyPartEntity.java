package org.mihalka.exercises_be.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mihalka.exercises_be.model.dto.BodyPartCreationDto;

@Entity
@Table(name="body_part")
@Getter
@Setter
@NoArgsConstructor
public class BodyPartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="body_part_id")
    private Long bodyPartId;

    @NotBlank(message = "You must provide a bodypart name")
    @Column(name = "body_part_name")
    private String bodyPartName;

public BodyPartEntity(BodyPartCreationDto dto){
    this.bodyPartName =dto.getBody_part_name();
}



}
