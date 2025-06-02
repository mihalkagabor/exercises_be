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
    private Long user_data_id;

    @NotBlank(message = "You must provide a firs name")
    @Column(name = "first_name")
    private String first_name;

    @NotBlank(message = "You must provide a last name")
    @Column(name="last_name")
    private String last_name;

    @NotNull(message = "You must provide your height")
    @Column(name="height")
    private Integer height;

    @NotNull(message = "You must provide your birth_year")
    @Column(name = "birth_year")
    private Integer birth_year;

    @NotNull(message = "You must provide your gender")
    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @NotNull()
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole user_role;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private AppUserEntity appUser;

    @OneToMany(mappedBy = "userData")
    private List<BodyWeightEntity> bodyWeightList=new ArrayList<>();

    @OneToMany (mappedBy = "userData")
    private List<WorkoutEntity> workoutList=new ArrayList<>();

    public UserDataEntity(UserDataCreationDto dto, AppUserEntity appUser) {
        this.first_name = dto.getFirst_name();
        this.last_name = dto.getLast_name();
        this.height = dto.getHeight();
        this.birth_year = dto.getBirth_year();
        this.sex = dto.getSex();
        this.user_role = dto.getUser_role();
        this.appUser = appUser;
    }

}
