package com.example.hollidayCottages.controller;

import com.example.hollidayCottages.Exceptions.ExceptionWithMessage;
import com.example.hollidayCottages.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService service;
    @GetMapping()
    public String showIndex() {
        return "index";
    }
    @PostMapping()
    public String getCottages(String checkIn, String checkOut, int guests, Model model ) {
        try {
           var cottageList = service.getCottages(checkIn,checkOut,guests);
           model.addAttribute("cottages",cottageList);
        } catch (ExceptionWithMessage e) {
            model.addAttribute("error",e.getMessage());
        }
        return "index";
    }
    @GetMapping("/regulamin")
    public String showRegulamin() {
        return "regulamin";
    }
    @GetMapping("/galeria")
    public String showGalley() {
        return "gallery";
    }

}
