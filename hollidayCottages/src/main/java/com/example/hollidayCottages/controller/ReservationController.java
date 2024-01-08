package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.contract.CusRes;
import com.example.hollidayCottages.contract.ReservationDto;
import com.example.hollidayCottages.model.Reservation;
import com.example.hollidayCottages.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {
    private final ReservationService service;
    private Reservation reservation=new Reservation();

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("/reservation/{CId}")
    public String showLoginForm(HttpSession session, Model model, @PathVariable int CId) {

        if (session == null || session.getAttribute("id") == null) {
            return "NoLogin";
        }
        reservation.setCottageId(CId);
        return "Reservation";
    }

    @PostMapping("/reservation")
    public String validateData(ReservationDto reservationDto, HttpSession session, Model model) {
        System.out.println("post");
        int id = (int) session.getAttribute("id");
        try {
            int CId = reservation.getCottageId();
            CusRes res = service.CheckData(reservationDto, id, CId);
            reservation = res.getReservation();
            session.setAttribute("Res",res.getReservation());
            model.addAttribute("Customer", res.getCustomer());
            model.addAttribute("Res", res.getReservation());
        } catch (ExceptionWithMessage e) {
            model.addAttribute("error", e.getMessage());
        }
        return "Reservation";
    }

    @PostMapping("/ResAdd")
    public String saveReservation(Model model,HttpSession session) {
        model.addAttribute("success", "rezerwacja udana");
        Reservation res= (Reservation) session.getAttribute("Res");
        System.out.println(res.getCustomerId());
        service.saveRes((Reservation) session.getAttribute("Res"));
        return "redirect:/reservation/"+res.getCottageId();
    }
}
