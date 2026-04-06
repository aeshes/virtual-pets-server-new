package com.aoizora.api.controller;

import com.aoizora.api.dto.RegistrationRequest;
import com.aoizora.api.dto.ServerInfoDTO;
import com.aoizora.service.PublicService;
import com.aoizora.service.exception.ServiceException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    private final PublicService publicService;

    public PublicController(PublicService publicService) {
        this.publicService = publicService;
    }

    @GetMapping("/serverInfo")
    public ServerInfoDTO getServerInfo() throws ServiceException {
        return publicService.getServerInfo();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/register")
    public void register(@RequestBody @Valid RegistrationRequest request) throws ServiceException {
        publicService.register(request);
    }
}
