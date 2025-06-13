package org.mihalka.exercises_be.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "workout")
@Getter
@Setter
@NoArgsConstructor
public class WorkoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="workout_id")
    private Long workoutId;

    @NotNull()
    @Column(name = "start_date")
    private LocalDateTime startDate;


    @NotNull()
    @Column(name = "end_date")
    private LocalDateTime endDate;


    @Column(insertable = false, updatable = false)
    private Integer workoutDuration;

    @NotNull()
    @Column(name = "exercise_amount")
    private Integer exercisesAmount;

    @NotNull()
    @Column(name = "exercise_weight",precision = 5,scale = 1)
    private BigDecimal exercisesWeight;

    @ManyToOne
    @JoinColumn(name = "exercises_id")
    private ExercisesEntity exercises;

    @ManyToOne
    @JoinColumn(name = "user_data_id")
    private UserDataEntity userData;


}
