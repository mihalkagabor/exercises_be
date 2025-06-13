package org.mihalka.exercises_be.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mihalka.exercises_be.model.dto.DifficultyLevelCreationDto;

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
    private Long difficultyLevelId;

    @NotNull (message = "You must provide a difficulty level")
    @Column(name="difficulty_level_level")
    private Integer difficultyLevelLevel;

    @NotBlank(message = "You must provide a description")
    @Column(name = "difficulty_level_description")
    private String difficultyLevelDescription;

    @OneToMany(mappedBy = "difficultyLevel")
    private List<ExercisesEntity> exercisesList =new ArrayList<>();

    public DifficultyLevelEntity(DifficultyLevelCreationDto difficultyLevelCreationDto){
        this.difficultyLevelDescription =difficultyLevelCreationDto.getDifficulty_level_description();
        this.difficultyLevelLevel =difficultyLevelCreationDto.getDifficulty_level_level();
    }
}
