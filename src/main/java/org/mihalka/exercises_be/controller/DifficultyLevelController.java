package org.mihalka.exercises_be.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mihalka.exercises_be.model.dto.DifficultyLevelCreationDto;
import org.mihalka.exercises_be.service.DifficultyLevelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/difficulty")
@AllArgsConstructor
public class DifficultyLevelController {

    private final DifficultyLevelService difficultyLevelService;


    @PostMapping("/create_DifficultyLevel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createDifficultyLevel(@RequestBody @Valid DifficultyLevelCreationDto difficultyLevelCreationDto){
        difficultyLevelService.createDifficultyLevel(difficultyLevelCreationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
