package com.aquawebdev.auctor.entity.validation.annotations;

import com.aquawebdev.auctor.entity.validation.PasswordValidator;
import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Login {
    String message() default "login should be from 1 to 15 characters";
}
