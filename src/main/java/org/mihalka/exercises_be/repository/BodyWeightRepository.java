package org.mihalka.exercises_be.repository;

import org.mihalka.exercises_be.model.dto.UserDataWeightListerDto;
import org.mihalka.exercises_be.model.entity.BodyWeightEntity;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyWeightRepository extends JpaRepository<BodyWeightEntity,Long> {
    List<BodyWeightEntity> findAllByUserData(UserDataEntity userData);
}
