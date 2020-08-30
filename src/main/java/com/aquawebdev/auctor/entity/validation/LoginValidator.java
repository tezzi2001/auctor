package com.aquawebdev.auctor.entity.validation;

import com.aquawebdev.auctor.entity.validation.annotations.Login;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<Login, String> {
    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return isInRange(1, 15, login);
    }

    private boolean isInRange(int from, int to, String login) {
        return login.length() >= from && login.length() <= to;
    }
}
