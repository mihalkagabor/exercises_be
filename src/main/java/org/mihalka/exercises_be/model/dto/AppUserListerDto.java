package org.mihalka.exercises_be.model.dto;

import lombok.Data;
import org.mihalka.exercises_be.model.entity.AppUserEntity;

@Data
public class AppUserListerDto {

    private Long user_id;
    private String name;
    private String email;

    public AppUserListerDto(AppUserEntity appUser){
        this.name=appUser.getName();
        this.user_id= appUser.getUserId();
        this.email= appUser.getEmail();
    }


}
