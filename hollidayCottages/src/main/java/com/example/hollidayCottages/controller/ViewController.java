package com.example.hollidayCottages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    private final AccountService service;

    @Autowired
    public ViewController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "loginForm";
    }
    @GetMapping("/singup")
    public String showSingUpForm() {
        return "singUpForm";
    }

}
