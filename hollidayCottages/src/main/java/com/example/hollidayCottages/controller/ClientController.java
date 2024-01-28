package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.model.Reservation;
import com.example.hollidayCottages.service.ClientService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class ClientController {
    private final ClientService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping()
    public String showReservations(HttpSession session, Model model) {
        if (session == null || session.getAttribute("id") == null) {
            return "redirect:/nologin";
        }
        try {
            List<Reservation> reservations = service.getAllReservation((Integer) session.getAttribute("id"));
            model.addAttribute("reservations", reservations);
        } catch (ExceptionWithMessage e) {
            LOGGER.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
        }
        return "account";
    }

    @PostMapping("/cancel/{id}")
    public String handleReservationAction(@PathVariable int id, Model model) {
        try {
            service.cancelReservation(id);
        } catch (ExceptionWithMessage e) {
            model.addAttribute("error", e.getMessage());
            LOGGER.error(e.getMessage());
        }
        return "redirect:/account";
    }
}
