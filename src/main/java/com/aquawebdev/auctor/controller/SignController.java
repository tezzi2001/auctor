package com.aquawebdev.auctor.controller;

import com.aquawebdev.auctor.entity.User;
import com.aquawebdev.auctor.service.SignService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class SignController {
    private final SignService signService;

    @GetMapping("/signUp")
    public String getSignUpPage(Model model, User user) {
        model.addAttribute("user", user);
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute("user") User user) {
        if (!signService.signUp(user)) {
            return "signUp";
        }
        return "signIn";
    }

    @GetMapping("/signIn")
    public String getSignInPage(Model model) {
        model.addAttribute("login", "");
        model.addAttribute("password", "");
        return "signIn";
    }

    @PostMapping("/signIn")
    public String signIn(Model model) {
        String login = (String) model.getAttribute("login");
        String password = (String) model.getAttribute("password");
        if (!signService.signIn(login, password)) {
            return "signIn";
        }
        return "articlesList";
    }

    @GetMapping("/resetPassword")
    public String getResetPasswordPage(Model model) {
        model.addAttribute("email", "");
        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public void resetPassword(@ModelAttribute("email") String email) {
        signService.resetPassword(email);
    }
}