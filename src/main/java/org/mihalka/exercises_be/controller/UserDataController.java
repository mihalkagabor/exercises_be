package org.mihalka.exercises_be.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mihalka.exercises_be.model.dto.WrapperUserData;
import org.mihalka.exercises_be.service.UserDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserDataController {
    private final UserDataService userDataService;

    @PostMapping("/create_UserData")
    public ResponseEntity<Void> createUserData(@Valid @RequestBody WrapperUserData wrapperUserData){
        userDataService.createATotalUserData(wrapperUserData.getUserDataCreationDto(),wrapperUserData.getBodyWeightCreationDto());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
