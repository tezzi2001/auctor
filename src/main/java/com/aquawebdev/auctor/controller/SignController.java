package com.aquawebdev.auctor.controller;

import com.aquawebdev.auctor.entity.User;
import com.aquawebdev.auctor.service.ISignService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class SignController {
    private ISignService signService;

    @GetMapping("/signUp")
    public String getSignUpPage(Model model, User user, Boolean isSuccessful) {
        model.addAttribute("user", user);
        model.addAttribute("isSuccessful", isSuccessful);
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(Model model) {
        boolean isSuccessful = (boolean) model.getAttribute("isSuccessful");
        if (isSuccessful) {
            return "signUp";
        }

        User user = (User) model.getAttribute("user");
        signService.signUp(user);
        return "signIn";
    }
}