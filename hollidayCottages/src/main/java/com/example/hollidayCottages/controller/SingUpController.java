package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.contract.UserDto;
import com.example.hollidayCottages.service.SingUpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@AllArgsConstructor
public class SingUpController {
    private final SingUpService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/singup")
    public String showSingUpForm() {
        return "singUpForm";
    }

    @PostMapping("/signup")
    public String registerUser(UserDto userDto, Model model) {
        LOGGER.info("sing post");
        try {
            service.registerUser(userDto);
            model.addAttribute("success", "Your account has been created successfully");
            return "singUpForm";
        } catch (ExceptionWithMessage e) {
            LOGGER.info("error sing post");
            model.addAttribute("error", e.getMessage());
            return "singUpForm";
        }
    }
}
