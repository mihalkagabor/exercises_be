package org.mihalka.exercises_be.service;

import org.mihalka.exercises_be.model.dto.UserDataWorkoutListerDto;
import org.mihalka.exercises_be.model.dto.WorkoutCreationDto;
import org.mihalka.exercises_be.model.dto.WorkoutFilterDto;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.model.entity.WorkoutEntity;
import org.mihalka.exercises_be.repository.ExercisesRepository;
import org.mihalka.exercises_be.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class WorkoutService {
private final WorkoutRepository workoutRepository;
private final ExercisesRepository exercisesRepository;
private final CurrentUserService currentUserService;

    public WorkoutService(WorkoutRepository workoutRepository, ExercisesRepository exercisesRepository,  CurrentUserService currentUserService) {
        this.workoutRepository = workoutRepository;
        this.exercisesRepository = exercisesRepository;
        this.currentUserService = currentUserService;
    }

    public void createWorkout(WorkoutCreationDto workoutCreationDto){
        UserDataEntity userData = currentUserService.getCurrentUserData();

        WorkoutEntity workoutEntity=new WorkoutEntity();
        workoutEntity.setExercisesAmount(workoutCreationDto.getExercise_amount());
        workoutEntity.setStartDate(workoutCreationDto.getStart_date());
        workoutEntity.setEndDate(workoutCreationDto.getEnd_date());
        workoutEntity.setExercisesWeight(workoutCreationDto.getExercise_weight());
        workoutEntity.setExercises(
                exercisesRepository.findById(workoutCreationDto.getExercises_id())
                        .orElseThrow(()->new RuntimeException("Exercise not found"))
        );
        workoutEntity.setUserData(userData);
         workoutRepository.save(workoutEntity);
    }



    public List<UserDataWorkoutListerDto> listUserExerciseWorkout(WorkoutFilterDto workoutFilterDto){
        UserDataEntity userData=currentUserService.getCurrentUserData();

        return workoutRepository.findAllByUserData(userData).stream()
                .filter(workout -> workoutFilterDto.getExerciseId() == null
                        || workout.getExercises().getExercisesId().equals(workoutFilterDto.getExerciseId()))
                .filter(workout -> workoutFilterDto.getBodyPartId() == null
                        || (workout.getExercises().getBodyPart() != null
                        && workout.getExercises().getBodyPart().getBodyPartId().equals(workoutFilterDto.getBodyPartId())))
                .filter(workout-> workoutFilterDto.getWorkoutDay()==null
                        || workout.getStartDate().toLocalDate().equals(workoutFilterDto.getWorkoutDay()) )
                .sorted(Comparator.comparing(WorkoutEntity::getStartDate).reversed())
                .map(UserDataWorkoutListerDto::new)
                .collect(Collectors.toList());

    }


//    public static Specification<ExercisesEntity> withFilters(ExercisesFilterDto filter) {
//        return (Root<ExercisesEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (filter.getExerciseId() != null) {
//                predicates.add(cb.equal(root.get("exercises_id"), filter.getExerciseId()));
//            }
//
//            if (filter.getExerciseDifficultyLevel() != null) {
//                predicates.add(cb.equal(
//                        root.get("difficultyLevel").get("difficulty_level_level"),
//                        filter.getExerciseDifficultyLevel()
//                ));
//            }
//
//            if (filter.getBodyPartId() != null) {
//                predicates.add(cb.equal(
//                        root.get("bodyPart").get("body_part_id"),
//                        filter.getBodyPartId()
//                ));
//            }
//
//            if (filter.getAccessoryId() != null) {
//                predicates.add(cb.equal(
//                        root.get("accessory").get("accessory_id"),
//                        filter.getAccessoryId()
//                ));
//            }
//
//            if (filter.getIsFreeWeight() != null) {
//                predicates.add(cb.equal(
//                        root.get("is_free_weight"),
//                        filter.getIsFreeWeight()
//                ));
//            }
//
//            return cb.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//}
}
