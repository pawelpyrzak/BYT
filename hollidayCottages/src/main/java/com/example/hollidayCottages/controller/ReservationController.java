package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.Exceptions.NotFoundException;
import com.example.hollidayCottages.contract.CusRes;
import com.example.hollidayCottages.model.Reservation;
import com.example.hollidayCottages.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@RequiredArgsConstructor
@Controller
public class ReservationController {
    private final ReservationService service;
    private Reservation reservation;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @GetMapping("/reservation")
    public String showReservationCottages() {
        return "reservationsAll";
    }


    @GetMapping("/reservation/{id}")
    public String showReservationForm(HttpSession session, @PathVariable int id) {
        if (session == null || session.getAttribute("id") == null) {
            LOGGER.error("No login");
            return "redirect:/nologin";
        }
        if(id<=0||id>4){
            LOGGER.error("bad id");
            throw new NotFoundException("złe id");
        }
        return "Reservation";
    }

    @PostMapping("/reservation/{cId}")
    public String validateData(Reservation reservation, HttpSession session, Model model, @PathVariable int cId) {
        try {
            LOGGER.info("Sprawdzanie danych");
            reservation.setCottageId(cId);
            CusRes res = service.CheckData(reservation, (Integer) session.getAttribute("id"));
            this.reservation = res.getReservation();
            model.addAttribute("Customer", res.getCustomer());
            model.addAttribute("Res", res.getReservation());
            LOGGER.info("Sprawdzono dane");
        } catch (ExceptionWithMessage e) {
            LOGGER.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
        }
        return "Reservation";
    }

    @PostMapping("/ResAdd")
    public String saveReservation(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success", "Rezerwacja przyjęta");
        LOGGER.info("Rezerwacja udana");
        service.saveRes(reservation);
        return "redirect:/reservation/" + reservation.getCottageId();
    }
}
