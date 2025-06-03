package org.mihalka.exercises_be.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mihalka.exercises_be.model.dto.AppUserCreationDto;
import org.mihalka.exercises_be.repository.AppUserRepository;
import org.mihalka.exercises_be.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;



    @PostMapping("/api/create_appUser")
    // a @Requestbody-t kell adni, hogy tudja a spring, hogy a JSON-t body-ban küldjük ki.
    public ResponseEntity <Void> createAppUser( @Valid @RequestBody AppUserCreationDto appUserCreationDTO){
        appUserService.appUserCreation(appUserCreationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
