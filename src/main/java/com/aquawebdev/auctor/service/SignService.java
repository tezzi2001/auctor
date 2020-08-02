package com.aquawebdev.auctor.service;

import com.aquawebdev.auctor.entity.User;

public interface SignService {
    boolean signIn(String login, String password);
    boolean signUp(User user);
    void resetPassword(String email);
}