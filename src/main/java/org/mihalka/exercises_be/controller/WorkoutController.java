package org.mihalka.exercises_be.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mihalka.exercises_be.model.dto.UserDataWorkoutListerDto;
import org.mihalka.exercises_be.model.dto.WorkoutCreationDto;
import org.mihalka.exercises_be.model.dto.WorkoutFilterDto;
import org.mihalka.exercises_be.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@AllArgsConstructor
public class WorkoutController {
    private final WorkoutService workoutService;

    @PostMapping("/create_workout")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createWorkout(@RequestBody @Valid WorkoutCreationDto workoutCreationDto){
        workoutService.createWorkout(workoutCreationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/filter")
    public List<UserDataWorkoutListerDto> filterWorkout(@RequestBody @Valid WorkoutFilterDto workoutFilterDto){
        return workoutService.listUserExerciseWorkout(workoutFilterDto);

    }
}
