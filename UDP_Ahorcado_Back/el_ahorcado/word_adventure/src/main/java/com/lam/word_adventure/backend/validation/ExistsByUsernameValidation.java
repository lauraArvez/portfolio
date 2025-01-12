/*package com.lam.word_adventure.backend.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lam.word_adventure.backend.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * clase que implementa la interfaz ExistsByUsername
 * @author Laura Arvez
 */
/*@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

    @Autowired
    private UserService service;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (service != null) {
            return true;
        }
        return !service.existsByUsername(username);
    }
    
}*/
