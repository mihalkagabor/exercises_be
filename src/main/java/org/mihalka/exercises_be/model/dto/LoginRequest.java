package org.mihalka.exercises_be.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class LoginRequest {
    private String identifier;
    private String password;

}
