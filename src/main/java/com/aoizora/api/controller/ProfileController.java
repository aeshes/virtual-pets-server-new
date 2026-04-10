package com.aoizora.api.controller;

import com.aoizora.api.dto.UserProfile;
import com.aoizora.config.security.UserDetailsImpl;
import com.aoizora.service.UserService;
import com.aoizora.service.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/site/user/profile")
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home(Locale locale, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) throws UserNotFoundException {
        logger.info("Welcome home! The client locale is {}.", locale);

        UserProfile userProfile = userService.getProfile(userDetails.getUserId());
        model.addAttribute("userProfile", userProfile);

        return "user/profile";
    }
}
