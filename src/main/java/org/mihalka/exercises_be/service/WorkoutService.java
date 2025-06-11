package org.mihalka.exercises_be.service;

import org.mihalka.exercises_be.model.dto.WorkoutCreationDto;
import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.model.entity.WorkoutEntity;
import org.mihalka.exercises_be.repository.AppUserRepository;
import org.mihalka.exercises_be.repository.ExercisesRepository;
import org.mihalka.exercises_be.repository.UserDataRepository;
import org.mihalka.exercises_be.repository.WorkoutRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class WorkoutService {
private final WorkoutRepository workoutRepository;
private final ExercisesRepository exercisesRepository;
private final UserDataRepository userDataRepository;
private final AppUserRepository appUserRepository;

    public WorkoutService(WorkoutRepository workoutRepository, ExercisesRepository exercisesRepository, UserDataRepository userDataRepository, AppUserRepository appUserRepository) {
        this.workoutRepository = workoutRepository;
        this.exercisesRepository = exercisesRepository;
        this.userDataRepository = userDataRepository;
        this.appUserRepository = appUserRepository;
    }



    public WorkoutEntity createWorkout(WorkoutCreationDto workoutCreationDto){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        AppUserEntity appUser = appUserRepository.findByEmail(username)
                .or(() -> appUserRepository.findByName(username))
                .orElseThrow(() -> {
                    System.out.println("User not found with identifier: " + username);
                    return new UsernameNotFoundException("User not found with identifier: " + username);
                });

        UserDataEntity userData = userDataRepository.findByAppUser(appUser)
                .orElseThrow(() -> new RuntimeException("There is no UserData"));

        WorkoutEntity workoutEntity=new WorkoutEntity();
        workoutEntity.setExercise_amount(workoutCreationDto.getExercise_amount());
        workoutEntity.setStart_date(workoutCreationDto.getStart_date());
        workoutEntity.setEnd_date(workoutCreationDto.getEnd_date());
        workoutEntity.setExercise_weight(workoutCreationDto.getExercise_weight());
        workoutEntity.setExercises(
                exercisesRepository.findById(workoutCreationDto.getExercises_id())
                        .orElseThrow(()->new RuntimeException("Exercise not found"))
        );
        workoutEntity.setUserData(userData);
        return workoutRepository.save(workoutEntity);

    }
}
