package com.aoizora.api.controller;

import com.aoizora.api.dto.ServerInfoDTO;
import com.aoizora.service.PublicService;
import com.aoizora.service.exception.ServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
