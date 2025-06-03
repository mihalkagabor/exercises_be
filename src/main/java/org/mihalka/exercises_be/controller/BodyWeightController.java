package org.mihalka.exercises_be.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mihalka.exercises_be.model.dto.BodyWeightCreationDto;
import org.mihalka.exercises_be.service.BodyWeightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/body_weight")
@AllArgsConstructor
public class BodyWeightController {
    private final BodyWeightService bodyWeightService;

    @PostMapping("/create_bodyWeight")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Void> createBodyWeight(@Valid @RequestBody BodyWeightCreationDto dto){
        bodyWeightService.createBodyWeight(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/test")
    public String test() {
        return "API működik";
    }


}
