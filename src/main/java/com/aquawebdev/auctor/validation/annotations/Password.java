package com.aquawebdev.auctor.validation.annotations;

import com.aquawebdev.auctor.validation.PasswordValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "password should be from 4 to 20 characters and uses upper and lower cases";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
