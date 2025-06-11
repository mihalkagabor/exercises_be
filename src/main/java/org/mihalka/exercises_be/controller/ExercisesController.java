package org.mihalka.exercises_be.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mihalka.exercises_be.model.dto.ExercisesCreationDto;
import org.mihalka.exercises_be.model.entity.ExercisesEntity;
import org.mihalka.exercises_be.service.ExercisesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercises")
@AllArgsConstructor
public class ExercisesController {
    private final ExercisesService exercisesService;


    @PostMapping("/create_exercises")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createExercises(@RequestBody @Valid ExercisesCreationDto exercisesCreationDto){
        exercisesService.createExercises(exercisesCreationDto);
        return new ResponseEntity<> (HttpStatus.CREATED);
    }
}
