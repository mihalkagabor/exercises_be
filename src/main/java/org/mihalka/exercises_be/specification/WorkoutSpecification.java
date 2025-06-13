package org.mihalka.exercises_be.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.mihalka.exercises_be.model.dto.UserDataWorkoutListerDto;
import org.mihalka.exercises_be.model.dto.WorkoutFilterDto;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.model.entity.WorkoutEntity;
import org.mihalka.exercises_be.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class WorkoutSpecification {

    private final CurrentUserService currentUserService = null;

    public UserDataEntity username=currentUserService.getCurrentUserData();


    public static Specification<WorkoutEntity> workoutFilter(WorkoutFilterDto workoutFilterDto,UserDataEntity userData){
        return (Root<WorkoutEntity> workoutEntityRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder ) ->{
            List<Predicate> predicates=new ArrayList<>();

            predicates.add(criteriaBuilder.equal(workoutEntityRoot.get("userData"),userData));

            if(workoutFilterDto.getExerciseId() !=null){
                predicates.add(criteriaBuilder.equal(workoutEntityRoot.get("exercises").get("exercisesId"),workoutFilterDto.getExerciseId()));
            }
            if(workoutFilterDto.getBodyPartId() != null) {
                predicates.add(criteriaBuilder.equal(workoutEntityRoot.get("exercises").get("bodyPart").get("bodyPartId"), workoutFilterDto.getBodyPartId()));
            }
            if (workoutFilterDto.getWorkoutDay() != null) {
                LocalDate day = workoutFilterDto.getWorkoutDay();
                LocalDateTime startOfDay = day.atStartOfDay();
                LocalDateTime endOfDay = day.atTime(LocalTime.MAX);

                predicates.add(criteriaBuilder.between(workoutEntityRoot.get("startDate"), startOfDay, endOfDay));
            }
            if(workoutFilterDto.getWorkout_duration() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(workoutEntityRoot.get("workoutDuration"),workoutFilterDto.getWorkout_duration()));
            }
            if(workoutFilterDto.getExercise_weight() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(workoutEntityRoot.get("exercisesWeight"),workoutFilterDto.getExercise_weight()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


    };
    }
}
