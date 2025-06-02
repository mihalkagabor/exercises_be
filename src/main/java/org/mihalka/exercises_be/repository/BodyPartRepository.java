package org.mihalka.exercises_be.repository;

import org.mihalka.exercises_be.model.entity.BodyPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyPartRepository extends JpaRepository<BodyPartEntity,Long> {
}
