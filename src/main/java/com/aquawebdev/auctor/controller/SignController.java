package com.aquawebdev.auctor.controller;

import com.aquawebdev.auctor.dto.UserDto;
import com.aquawebdev.auctor.service.SignService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class SignController {
    private final SignService signService;

    @GetMapping("/signUp")
    public String getSignUpPage(Model model, UserDto user) {
        model.addAttribute("user", user);
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "signUp";
        }
        signService.signUp(user);
        return "signIn";
    }

    @GetMapping("/signIn")
    public String getSignInPage(@RequestParam(name = "error", required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "signIn";
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

    @GetMapping("/")
    public String getArticle() {
        return "articlesList";
    }
}