package com.aquawebdev.auctor.validation;

import com.aquawebdev.auctor.repository.UserRepository;
import com.aquawebdev.auctor.validation.annotations.UniqueEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.findByEmail(email).isPresent();
    }
}
