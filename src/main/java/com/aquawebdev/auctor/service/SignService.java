package com.aquawebdev.auctor.service;

import com.aquawebdev.auctor.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SignService extends UserDetailsService {
    boolean signUp(User user);
    boolean resetPassword(String email);
}