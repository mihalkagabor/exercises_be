package org.mihalka.exercises_be.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mihalka.exercises_be.model.dto.UserDataCreationDto;
import org.mihalka.exercises_be.model.enums.Sex;
import org.mihalka.exercises_be.model.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user_data")
@Getter
@Setter
@NoArgsConstructor
public class UserDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_data_id")
    private Long userDataId;

    @NotBlank(message = "You must provide a firs name")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "You must provide a last name")
    @Column(name="last_name")
    private String lastName;

    @NotNull(message = "You must provide your height")
    @Column(name="height")
    private Integer height;

    @NotNull(message = "You must provide your birth_year")
    @Column(name = "birth_year")
    private Integer birthYear;

    @NotNull(message = "You must provide your gender")
    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @NotNull()
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private AppUserEntity appUser;

    @OneToMany(mappedBy = "userData")
    private List<BodyWeightEntity> bodyWeightList=new ArrayList<>();

    @OneToMany (mappedBy = "userData")
    private List<WorkoutEntity> workoutList=new ArrayList<>();

    public UserDataEntity(UserDataCreationDto dto, AppUserEntity appUser) {
        this.firstName = dto.getFirst_name();
        this.lastName = dto.getLast_name();
        this.height = dto.getHeight();
        this.birthYear = dto.getBirth_year();
        this.sex = dto.getSex();
        this.userRole = dto.getUser_role();
        this.appUser = appUser;
    }

}
