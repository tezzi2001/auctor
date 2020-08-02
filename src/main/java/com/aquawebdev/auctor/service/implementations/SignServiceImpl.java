package com.aquawebdev.auctor.service.implementations;

import com.aquawebdev.auctor.entity.Role;
import com.aquawebdev.auctor.entity.User;
import com.aquawebdev.auctor.repository.UserRepository;
import com.aquawebdev.auctor.service.SignService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SignServiceImpl implements SignService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public boolean signIn(String login, String password) {
        Optional<User> persistentUser = userRepository.findByLogin(login);
        return persistentUser.filter(user -> passwordEncoder.matches(password, user.getPassword())).isPresent();
    }

    @Override
    public boolean signUp(User user) {
        Optional<User> persistentUser = userRepository.findByLogin(user.getLogin());

        if (persistentUser.isPresent()) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return true;
    }

    @Override
    public void resetPassword(String email) {
    }
}