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
    private Long workout_id;

    @NotNull()
    @Column(name = "start_date")
    private LocalDateTime start_date;


    @NotNull()
    @Column(name = "end_date")
    private LocalDateTime end_date;


    @Column(insertable = false, updatable = false)
    private Integer workout_duration;

    @NotNull()
    @Column(name = "exercise_amount")
    private Integer exercise_amount;

    @NotNull()
    @Column(name = "exercise_weight",precision = 5,scale = 1)
    private BigDecimal exercise_weight;

    @ManyToOne
    @JoinColumn(name = "exercises_id")
    private ExercisesEntity exercises;

    @ManyToOne
    @JoinColumn(name = "user_data_id")
    private UserDataEntity userData;


}
