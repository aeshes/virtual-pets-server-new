package com.aoizora.api.controller;

import com.aoizora.api.dto.LoginRequest;
import com.aoizora.api.dto.LoginResponse;
import com.aoizora.api.dto.RegistrationRequest;
import com.aoizora.api.dto.ServerInfoDTO;
import com.aoizora.config.security.UserDetailsImpl;
import com.aoizora.service.PublicService;
import com.aoizora.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    private final PublicService publicService;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public PublicController(PublicService publicService, AuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository) {
        this.publicService = publicService;
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @GetMapping("/serverInfo")
    public ServerInfoDTO getServerInfo() throws ServiceException {
        return publicService.getServerInfo();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/register")
    public void register(@RequestBody @Valid RegistrationRequest registration) throws ServiceException {
        publicService.register(registration);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest log, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(log.login(), log.password(), null);
        Authentication result = authenticationManager.authenticate(authentication);

        publicService.login(log);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(result);

        securityContextRepository.saveContext(securityContext, request, response);

        UserDetailsImpl userDetails = (UserDetailsImpl) result.getPrincipal();

        return new LoginResponse(true,
                null,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getName());
    }
}
