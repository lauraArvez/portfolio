/*package com.lam.word_adventure.backend.validation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * validación por username, mensaje personalizado
 * @author Laura Arvez
 */

/*@Constraint(validatedBy = ExistsByUsernameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUsername {

    //reutilizo la interface @interface NotBlank para personalizar el mensaje
    String message() default "ya existe en la base de datos!, escoja otro username!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}*/