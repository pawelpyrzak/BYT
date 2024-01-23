package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.model.Reservation;
import com.example.hollidayCottages.service.ClientService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class ClientController {
    private final ClientService service;

    @GetMapping()
    public String showReservations(HttpSession session, Model model) {
        if (session == null || session.getAttribute("id") == null) {
            return "NoLogin";
        }
        try {
            List<Reservation> reservations = service.getAllReservation((Integer) session.getAttribute("id"));
            model.addAttribute("reservations", reservations);
        } catch (ExceptionWithMessage e) {
            model.addAttribute("error", e.getMessage());
        }
        return "account";
    }

    @PostMapping("/res/cancel/{id}")
    public String handleReservationAction(@PathVariable int id, Model model) {
        try {
            service.cancelReservation(id);
        } catch (ExceptionWithMessage e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/account";
    }
}
