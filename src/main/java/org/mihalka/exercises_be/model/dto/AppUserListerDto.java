package org.mihalka.exercises_be.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.mihalka.exercises_be.model.entity.AppUserEntity;

@Data
public class AppUserListerDto {

    private Long user_id;
    private String name;
    private String email;

    public AppUserListerDto(AppUserEntity appUser){
        this.name=appUser.getName();
        this.user_id= appUser.getUser_id();
        this.email= appUser.getEmail();
    }


}
