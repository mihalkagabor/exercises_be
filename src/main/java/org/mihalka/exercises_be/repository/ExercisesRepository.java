package org.mihalka.exercises_be.repository;

import org.mihalka.exercises_be.model.entity.ExercisesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExercisesRepository  extends JpaRepository<ExercisesEntity,Long> {
}
