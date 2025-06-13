package org.mihalka.exercises_be.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.mihalka.exercises_be.model.entity.AccessoryEntity;

@Getter
@Setter
@Data
public class AccessoryCreationDto {

@NotBlank(message = "You must provide a accessory name")
    private String accessory_name;

public AccessoryCreationDto(AccessoryEntity accessoryEntity){
    this.accessory_name=accessoryEntity.getAccessoryName();
}

}
