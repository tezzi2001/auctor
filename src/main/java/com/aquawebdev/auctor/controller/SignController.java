package com.aquawebdev.auctor.controller;

import com.aquawebdev.auctor.entity.User;
import com.aquawebdev.auctor.service.ISignService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class SignController {
    private final ISignService signService;

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
}