package org.mihalka.exercises_be.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.mihalka.exercises_be.model.entity.BodyPartEntity;

@Getter
@Setter
@Data
public class BodyPartCreationDto {

    @NotBlank(message = "You must provide a body part name")
    private String body_part_name;

    public BodyPartCreationDto(BodyPartEntity bodyPartEntity){
        this.body_part_name=bodyPartEntity.getBodyPartName();
    }


}
