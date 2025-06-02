package org.mihalka.exercises_be.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="difficulty_level")
public class DifficultyLevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "difficulty_level_id")
    private Long difficulty_level_id;

    @NotNull (message = "You must provide a difficulty level")
    @Column(name="difficulty_level_level")
    private Integer difficulty_level_level;

    @NotBlank(message = "You must provide a description")
    @Column(name = "difficulty_level_description")
    private String difficulty_level_description;

    @OneToMany(mappedBy = "difficultyLevel")
    private List<ExercisesEntity> exercisesList =new ArrayList<>();

}
