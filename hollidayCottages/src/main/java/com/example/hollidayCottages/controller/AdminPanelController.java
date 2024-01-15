package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.Exceptions.NotFoundException;
import com.example.hollidayCottages.contract.CusRes;
import com.example.hollidayCottages.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminPanelController {
    private final AdminService service;
    private String url;

    public AdminPanelController(AdminService service) {
        this.service = service;
    }

    @GetMapping()
    public String adminDashboard(HttpSession session, Model model) throws NotFoundException {
        if (session != null && session.getAttribute("Role") != null && session.getAttribute("Role").equals("Admin")) {
            return "admin";
        } else {
            throw new NotFoundException("Brak uprawnień");
        }
    }

    @GetMapping("/res")
    public String getReservationsForPeriodAndCottage(@RequestParam String startDate, @RequestParam String endDate, @RequestParam int cottageId, Model model) {
        if (startDate.isEmpty() || endDate.isEmpty() || cottageId < 0 || cottageId > service.getLastId()) {
            model.addAttribute("error", "Nie poprawne dane");
            return "admin";
        }
        url = "/res?startDate=" + startDate + "&endDate=" + endDate + "&cottageId=" + cottageId;
        List<CusRes> cusResList = service.getReservationAndCustomer(startDate, endDate, cottageId);
        model.addAttribute("cusResList", cusResList);
        return "admin";
    }

    @PostMapping("/res/{action}/{id}")
    public String handleReservationAction(@PathVariable int id, @PathVariable String action, Model model) {
        try {

            service.changeStatusOfReservation(id, action);
        } catch (ExceptionWithMessage e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/admin" + url;
    }
}
