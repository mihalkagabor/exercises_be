package org.mihalka.exercises_be.model.dto;

import lombok.Data;
import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.model.enums.Sex;
import org.mihalka.exercises_be.model.enums.UserRole;

@Data
public class UserDataListerDto {

    private Long user_data_id;

    private String first_name;

    private String last_name;

    private Integer birth_year;

    private String sex;

    private String user_role;

    private Long appUser_id;


    public UserDataListerDto(UserDataEntity userData){
        this.user_data_id=userData.getUser_data_id();
        this.first_name=userData.getFirst_name();
        this.last_name=userData.getLast_name();
        this.birth_year=userData.getBirth_year();
        this.sex=userData.getSex().toString();
        this.user_role=userData.getUser_role().toString();
        this.appUser_id=userData.getAppUser().getUser_id();
    }

}
