package org.mihalka.exercises_be.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mihalka.exercises_be.model.dto.AccessoryCreationDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="accessory")
@NoArgsConstructor
public class AccessoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accessory_id")
    private Long accessoryId;

    @NotBlank(message = "You must provide an accessory name")
    @Column(name = "accessory_name")
    private String accessoryName;


    @OneToMany(mappedBy = "accessory")
    private List<ExercisesEntity> exercisesList =new ArrayList<>();

    public AccessoryEntity(AccessoryCreationDto dto){
        this.accessoryName =dto.getAccessory_name();
    }

}
