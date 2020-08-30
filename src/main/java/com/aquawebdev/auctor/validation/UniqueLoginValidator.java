package com.aquawebdev.auctor.validation;

import com.aquawebdev.auctor.repository.UserRepository;
import com.aquawebdev.auctor.validation.annotations.UniqueLogin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.findByLogin(login).isPresent();
    }
}
