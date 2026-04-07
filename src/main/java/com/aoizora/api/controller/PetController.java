package com.aoizora.api.controller;

import com.aoizora.api.dto.CreatePetRequest;
import com.aoizora.api.dto.DrinkRequest;
import com.aoizora.api.dto.SatietyRequest;
import com.aoizora.config.security.UserDetailsImpl;
import com.aoizora.service.PetService;
import com.aoizora.service.exception.ServiceException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid CreatePetRequest request) throws ServiceException {
        petService.create(userDetails.getUserId(), request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/delete/{petId}", method = RequestMethod.DELETE)
    public void delete(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable @Min(1) Integer petId) throws ServiceException {
        petService.delete(userDetails.getUserId(), petId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/drink", method = RequestMethod.POST)
    public void drink(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid DrinkRequest request) throws ServiceException {
        petService.drink(userDetails.getUserId(), request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/satiety", method = RequestMethod.POST)
    public void eat(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid SatietyRequest request) throws ServiceException {
        petService.satiety(userDetails.getUserId(), request);
    }
}
