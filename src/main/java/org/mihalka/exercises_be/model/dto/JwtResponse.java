package org.mihalka.exercises_be.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class JwtResponse {

    private final String token;

    public JwtResponse(String token) {
        this.token=token;
    }

}
