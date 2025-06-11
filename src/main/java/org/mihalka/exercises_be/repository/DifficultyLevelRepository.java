package org.mihalka.exercises_be.repository;

import org.mihalka.exercises_be.model.entity.DifficultyLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficultyLevelRepository extends JpaRepository<DifficultyLevelEntity,Long> {
public DifficultyLevelEntity findByDifficulty_level_id(Long id);

}
