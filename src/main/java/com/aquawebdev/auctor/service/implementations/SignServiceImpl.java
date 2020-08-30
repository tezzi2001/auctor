package com.aquawebdev.auctor.service.implementations;

import com.aquawebdev.auctor.dto.UserDto;
import com.aquawebdev.auctor.entity.Role;
import com.aquawebdev.auctor.entity.User;
import com.aquawebdev.auctor.repository.UserRepository;
import com.aquawebdev.auctor.service.SignService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@AllArgsConstructor
public class SignServiceImpl implements SignService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void signUp(UserDto userDto) {
        User user = userDto.toUser();
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public boolean resetPassword(String email) {
        return !"".equals(email);
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        return userRepository
                .findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}