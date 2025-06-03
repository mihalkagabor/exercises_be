package org.mihalka.exercises_be.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mihalka.exercises_be.model.dto.BodyPartCreationDto;
import org.mihalka.exercises_be.service.BodyPartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/body_part")
@AllArgsConstructor
public class BodyPartController {

    private final BodyPartService bodyPartService;


    @PostMapping("/create_bodyPart")
    @PreAuthorize("hasAnyRole('ADMIN','TRAINER')")
    public ResponseEntity<Void> createBodyPart(@RequestBody @Valid BodyPartCreationDto dto){
        bodyPartService.createBodyPart(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
