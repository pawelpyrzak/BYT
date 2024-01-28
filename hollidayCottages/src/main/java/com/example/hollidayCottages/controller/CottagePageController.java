package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.model.Cottage;
import com.example.hollidayCottages.service.CottagePageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CottagePageController {
    private final CottagePageService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/domek/{Id}")
    public String showPage(Model model, @PathVariable int Id) {
        if (Id < 0 || Id > service.getLastId()) {
            model.addAttribute("error", "Nie poprawne dane");
            LOGGER.error("bad id");
        }else {
            try {
                Cottage cottage = service.getCottage(Id);
                model.addAttribute("cottage", cottage);
            } catch (ExceptionWithMessage e) {
                LOGGER.error(e.getMessage());
                model.addAttribute("error", e.getMessage());
            }
        }
        return "domki";
    }
}
