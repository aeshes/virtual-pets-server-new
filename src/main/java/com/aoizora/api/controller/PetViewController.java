package com.aoizora.api.controller;

import com.aoizora.service.PetService;
import com.aoizora.service.exception.PetNotFoundException;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/site")
public class PetViewController {

    private static final Logger logger = LoggerFactory.getLogger(PetViewController.class);

    private final PetService petService;

    public PetViewController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/information/pet")
    public String petInfo(@RequestParam(value = "id")  @Min(1) Integer id, Model model) throws PetNotFoundException {
        model.addAttribute("pet", petService.petInformationPage(id));

        return "information/pet";
    }
}
