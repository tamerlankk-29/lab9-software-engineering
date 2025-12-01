package com.example.lab9.web;

import com.example.lab9.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public String list(Model model) {
        model.addAttribute("countries", countryService.findAll());
        return "countries/list";
    }
}
