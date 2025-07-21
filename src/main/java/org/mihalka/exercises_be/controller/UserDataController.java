package org.mihalka.exercises_be.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mihalka.exercises_be.model.dto.UserDataListerDto;
import org.mihalka.exercises_be.model.dto.WrapperUserData;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.service.UserDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userData")
@AllArgsConstructor
public class UserDataController {
    private final UserDataService userDataService;

    @PostMapping("/create_UserData")
    public ResponseEntity<Void> createUserData(@Valid @RequestBody WrapperUserData wrapperUserData){
        userDataService.createATotalUserData(wrapperUserData.getUserDataCreationDto(),wrapperUserData.getBodyWeightCreationDto());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/lister")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDataListerDto>>userDataLister(){
        return ResponseEntity.ok(userDataService.listUserData());
    }
    @GetMapping("/myUserData")
    public ResponseEntity<UserDataListerDto>myUserDataLister(){
        return ResponseEntity.ok(userDataService.myUserData());
    }
}
