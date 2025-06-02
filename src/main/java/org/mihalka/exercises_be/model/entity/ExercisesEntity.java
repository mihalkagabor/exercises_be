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
@Table(name="exercises")
@Getter
@Setter
@NoArgsConstructor
public class ExercisesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="exercises_id")
    private Long exercises_id;

    @NotBlank(message = "You must provide an exercise name")
    @Column(name = "exercise_name")
    private String exercise_name;

    @NotNull(message = "You must provide is this exercise is free weight?")
    @Column(name = "is_free_weight")
    private Boolean is_free_weight;

    @ManyToOne
    @JoinColumn(name="difficulty_level_id")
    private DifficultyLevelEntity difficultyLevel;

    @ManyToOne
    @JoinColumn(name="accessory_id")
    private AccessoryEntity accessory;


@OneToMany (mappedBy = "exercises")
    private List<WorkoutEntity> workoutList=new ArrayList<>();


}
