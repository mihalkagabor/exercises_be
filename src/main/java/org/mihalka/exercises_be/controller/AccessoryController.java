package org.mihalka.exercises_be.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mihalka.exercises_be.model.dto.AccessoryCreationDto;
import org.mihalka.exercises_be.service.AccessoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accessory")
@AllArgsConstructor
public class AccessoryController {
    private final AccessoryService accessoryService;

    //TODO: Végpont ellenőrzés még nem készült el, Tesztelni kell postmanben
    @PostMapping("/create_Accessory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createAccessory(@RequestBody @Valid AccessoryCreationDto accessoryCreationDto){
        accessoryService.createAccessory(accessoryCreationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
