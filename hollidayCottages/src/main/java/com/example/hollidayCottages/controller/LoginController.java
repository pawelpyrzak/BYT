package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.model.User;
import com.example.hollidayCottages.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LoginController {
    private final LoginService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/logins")
    public String showLoginForm() {
        return "loginForm";
    }

    @PostMapping("/logins")
    public String loginUser(Model model, HttpSession session, String email, String password) {
        try {
            User user = service.authenticateUser(email, password);
            session.setAttribute("id", user.getId());
            session.setAttribute("Role",user.getUserType().getName());
            var customer= service.getCustomerById(user.getId());
            model.addAttribute("name", customer.isPresent() ? customer.get().getName() : "Admin");

        } catch (ExceptionWithMessage e) {
            LOGGER.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
        }
        return "loginForm";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/logins";
    }
    @GetMapping("/login")
    public String loginBadUrl() {
        return "redirect:/logins";
    }
}
