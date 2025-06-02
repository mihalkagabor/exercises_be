package org.mihalka.exercises_be.validation;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Validator {


    private boolean hasDigit(String password){
       return password.chars().anyMatch(Character::isDigit);
    }
    private boolean hasLower(String password){
       return password.chars().anyMatch(Character::isLowerCase);
    }
    private boolean hasUpper(String password){
       return password.chars().anyMatch(Character::isUpperCase);
    }

    public List<String> PasswordValidate(String password){
        List<String> errors = new ArrayList<> ();
        if(password.length()<8){
            errors.add("A jelszónak legalább 8 karakter hosszúnak kell lennie.");
        }
          if ( !hasLower(password) ) {
            errors.add("A jelszónak tartalmaznia kell kisbetűt");
        }
        if (!hasUpper(password)){
            errors.add("A jelszónak tartalamznia kell nagybetűt");
        }
        if (!hasDigit(password)) {
            errors.add("A jelszónak tartalamznia kell számokat");
        }
        return errors;
    }
}
