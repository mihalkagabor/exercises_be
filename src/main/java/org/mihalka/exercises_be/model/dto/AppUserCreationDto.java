package org.mihalka.exercises_be.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppUserCreationDto {

    @NotBlank(message = "Adj meg nevet te kretén")
    private String name;

    @NotBlank(message = "Az email mező nem lehet üres")
    @Pattern(regexp = "^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$", message = "Nem megfelelő email formátum")
    private String email;


    private String password_hash;



}
