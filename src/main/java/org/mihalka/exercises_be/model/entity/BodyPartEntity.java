package org.mihalka.exercises_be.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="body_part")
@Getter
@Setter
@NoArgsConstructor
public class BodyPartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long body_part_id;

    @NotBlank(message = "You must provide a bodypart naem")
    @Column(name = "body_part_name")
    private String budy_part_name;





}
