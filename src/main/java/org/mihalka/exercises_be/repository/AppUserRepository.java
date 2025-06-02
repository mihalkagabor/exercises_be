package org.mihalka.exercises_be.repository;

import jakarta.validation.constraints.NotBlank;
import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {
    Optional<AppUserEntity> findByEmail(String email);

    Optional<AppUserEntity> findByName(@NotBlank(message = "You must provide a user name") String name);


}
