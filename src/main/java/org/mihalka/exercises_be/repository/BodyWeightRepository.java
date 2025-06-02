package org.mihalka.exercises_be.repository;

import org.mihalka.exercises_be.model.entity.BodyWeightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyWeightRepository extends JpaRepository<BodyWeightEntity,Long> {
}
