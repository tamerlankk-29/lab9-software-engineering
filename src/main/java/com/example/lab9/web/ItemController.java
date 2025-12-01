package com.example.lab9.web;

import com.example.lab9.service.CountryService;
import com.example.lab9.service.ItemService;
import com.example.lab9.web.dto.ItemCreateDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
    private final ItemService itemService;
    private final CountryService countryService;

    public ItemController(ItemService itemService, CountryService countryService) {
        this.itemService = itemService;
        this.countryService = countryService;
    }

    @GetMapping("/items")
    public String list(Model model) {
        model.addAttribute("items", itemService.findAllWithManufacturer());
        return "items/list";
    }

    @GetMapping("/items/new")
    public String newItemForm(Model model) {
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("form", new ItemCreateDto());
        return "items/new";
    }

    @PostMapping("/items")
    public String create(@ModelAttribute("form") ItemCreateDto form) {
        itemService.create(form.getName(), form.getPrice(), form.getQuantity(), form.getManufacturerId());
        return "redirect:/items";
    }
}
