package com.aquawebdev.auctor.service;

import com.aquawebdev.auctor.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SignService extends UserDetailsService {
    boolean signUp(UserDto userDto);
    boolean resetPassword(String email);
}