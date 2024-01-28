package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.contract.UserFullDto;
import com.example.hollidayCottages.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/signup")
    public String showSingUpForm() {
        return "signUpForm";
    }

    @PostMapping("/signup")
    public String registerUser(UserFullDto userFullDto, Model model) {
        try {
            service.registerUser(userFullDto);
            model.addAttribute("success", "Your account has been created successfully");
            LOGGER.info("Your account has been created successfully");

        } catch (ExceptionWithMessage e) {
            model.addAttribute("error", e.getMessage());
            LOGGER.error(e.getMessage());
        }
        return "signUpForm";
    }
}
