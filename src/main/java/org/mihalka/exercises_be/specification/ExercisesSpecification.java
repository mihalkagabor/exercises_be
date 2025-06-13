package org.mihalka.exercises_be.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.mihalka.exercises_be.model.dto.ExercisesFilterDto;
import org.mihalka.exercises_be.model.entity.ExercisesEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class ExercisesSpecification {

    public static Specification<ExercisesEntity> exerciseFilter (ExercisesFilterDto exercisesFilterDto){
        return (Root<ExercisesEntity> exercisesEntityRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder ) ->{
            List<Predicate> predicates=new ArrayList<>();

            //Megnézzük, hogy a bekapott DTO-ban lévő adat nem null!
            //Amennyiben nem null, hozzá adjuk a Predictate Listához
            //A criteria builderrel tudjuk az equal, nagyobb kisebb, és hasonló logikákat behozni .

            if(exercisesFilterDto.getExerciseId() != null){
                predicates.add(criteriaBuilder.equal(exercisesEntityRoot.get("exercisesId"), exercisesFilterDto.getExerciseId()));
            }
            if(exercisesFilterDto.getExercisesName() != null) {
                predicates.add(criteriaBuilder.equal(exercisesEntityRoot.get("exercisesName"), exercisesFilterDto.getExercisesName()));
            }

            if(exercisesFilterDto.getIsFreeWeight() != null){
                predicates.add(criteriaBuilder.equal(exercisesEntityRoot.get("isFreeWeight"),exercisesFilterDto.getIsFreeWeight()));
            }
            if(exercisesFilterDto.getExerciseDifficultyLevel() != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(exercisesEntityRoot.get("difficultyLevel").get("difficultyLevelId"),exercisesFilterDto.getExerciseDifficultyLevel()));
            }
            if(exercisesFilterDto.getBodyPartId() != null) {
                predicates.add(criteriaBuilder.equal(exercisesEntityRoot.get("bodyPart").get("bodyPartId"),exercisesFilterDto.getBodyPartId()));
            }
            if(exercisesFilterDto.getAccessoryId() != null){
                predicates.add(criteriaBuilder.equal(exercisesEntityRoot.get("accessory").get("accessoryId"),exercisesFilterDto.getAccessoryId()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

}

}
