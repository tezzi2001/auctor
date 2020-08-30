package com.aquawebdev.auctor.validation.annotations;

import com.aquawebdev.auctor.validation.UniqueLoginValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLoginValidator.class)
public @interface UniqueLogin {
    String message() default "such login is already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

