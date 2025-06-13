package org.mihalka.exercises_be.repository;

import org.mihalka.exercises_be.model.dto.UserDataWorkoutListerDto;
import org.mihalka.exercises_be.model.entity.ExercisesEntity;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.model.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity,Long> , JpaSpecificationExecutor<WorkoutEntity> {

    public List<WorkoutEntity> findAllByUserData(UserDataEntity userData);

    public List<WorkoutEntity> findAllByUserDataAndExercises (UserDataEntity userData, ExercisesEntity exercisesEntity );


}

