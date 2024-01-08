package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.NotFoundException;
import com.example.hollidayCottages.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.hollidayCottages.contract.ReservationDto;
import com.example.hollidayCottages.model.Reservation;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Controller
@AllArgsConstructor
public class AdminPanelController {
    final ReservationService service;
    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
    public String adminDashboard(HttpSession session, Model model) throws NotFoundException {
        if (session != null&&session.getAttribute("Role") != null&&session.getAttribute("Role").equals("Admin")) {
            return "admin";
        } else {
            throw new NotFoundException("Brak uprawnień");
        }
    }
}
