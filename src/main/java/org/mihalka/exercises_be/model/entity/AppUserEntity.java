package org.mihalka.exercises_be.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mihalka.exercises_be.model.dto.AppUserCreationDto;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="app_user")
@Getter
@Setter
@NoArgsConstructor
public class AppUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @NotBlank(message = "You must provide a user name")
    @Column(name ="name")
    private String name;

    @NotBlank(message = "You must provide an email address")
    @Column(name="email")
    private String email;

    @NotBlank(message = "You must provide a correct password")
    @Column(name="password_hash")
    private String password_hash;

    @Column(name="created_at")
    private LocalDateTime created_at;

@OneToOne(mappedBy = "appUser")
    private UserDataEntity userData;

@OneToMany(mappedBy = "user")
private List<RefreshTokenEntity> refreshTokens;

    //AppUser entity létrehozásához szükségeses construktor
    public AppUserEntity (AppUserCreationDto dto){
        this.name= dto.getName();
        this.email=dto.getEmail();
        this.password_hash= dto.getPassword_hash();
        this.setCreated_at(LocalDateTime.now());
    }
}
