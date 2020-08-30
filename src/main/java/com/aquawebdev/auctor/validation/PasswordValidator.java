package com.aquawebdev.auctor.validation;

import com.aquawebdev.auctor.validation.annotations.Password;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return isInRange(4, 20, password)
                && hasLowerCaseSymbols(password)
                && hasUpperCaseSymbols(password);
    }

    private boolean isInRange(int from, int to, String password) {
        return password.length() >= from && password.length() <= to;
    }

    private boolean hasLowerCaseSymbols(String password) {
        return Pattern.compile("[a-z]").matcher(password).find();
    }

    private boolean hasUpperCaseSymbols(String password) {
        return Pattern.compile("[A-Z]").matcher(password).find();
    }
}
