package org.mihalka.exercises_be.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mihalka.exercises_be.model.dto.AppUserCreationDto;
import org.mihalka.exercises_be.model.dto.AppUserListerDto;
import org.mihalka.exercises_be.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appUsers")
@AllArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;



    @PostMapping("/create_appUsers")
    // a @Requestbody-t kell adni, hogy tudja a spring, hogy a JSON-t body-ban küldjük ki.
    public ResponseEntity <Void> createAppUser( @Valid @RequestBody AppUserCreationDto appUserCreationDTO){
        appUserService.appUserCreation(appUserCreationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list_appusers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AppUserListerDto>> listAppUser(){
        return ResponseEntity.ok(appUserService.listAppUsers());
    }
}
