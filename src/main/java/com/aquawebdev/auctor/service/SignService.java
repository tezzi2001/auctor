package com.aquawebdev.auctor.service;

import com.aquawebdev.auctor.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SignService extends UserDetailsService {
    boolean signIn(String login, String password);
    boolean signUp(User user);
    void resetPassword(String email);
}