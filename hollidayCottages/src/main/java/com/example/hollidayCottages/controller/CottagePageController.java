package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.Exceptions.NotFoundException;
import com.example.hollidayCottages.model.Cottage;
import com.example.hollidayCottages.service.CottagePageService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CottagePageController {
private final CottagePageService service;
    @GetMapping("/domek/{Id}")
    public String showPage( Model model, @PathVariable int Id) {
        if (Id < 0 || Id > service.getLastId()) {
            model.addAttribute("error", "Nie poprawne dane");
            return "domki";
        }
        try {
            Cottage cottage=service.getCottage(Id);
            model.addAttribute("cottage",cottage);
        } catch (ExceptionWithMessage e) {
            model.addAttribute("error", e.getMessage());
        }
        return "domki";
    }
}
