package org.mihalka.exercises_be.model.dto;

import lombok.Data;
import org.mihalka.exercises_be.model.entity.UserDataEntity;


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
        this.user_data_id=userData.getUserDataId();
        this.first_name=userData.getFirstName();
        this.last_name=userData.getLastName();
        this.birth_year=userData.getBirthYear();
        this.sex=userData.getSex().toString();
        this.user_role=userData.getUserRole().toString();
        this.appUser_id=userData.getAppUser().getUserId();
    }

}
