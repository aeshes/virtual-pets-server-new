package com.aoizora.api.controller;

import com.aoizora.api.dto.RegistrationRequest;
import com.aoizora.service.PublicService;
import com.aoizora.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
@RequestMapping("/site")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final PublicService publicService;

    public HomeController(PublicService publicService) {
        this.publicService = publicService;
    }

    @GetMapping("/home")
    public String home(Locale locale) {
        logger.info("Welcome home! The client locale is {}.", locale);
        return "home";
    }

    @PostMapping("/register")
    public String registerForm(@RequestParam String login,
                               @RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam(defaultValue = "1.0") String version,
                               RedirectAttributes redirectAttributes) {

        RegistrationRequest request = new RegistrationRequest(login, name, password, email, version);

        try {
            publicService.register(request);
            redirectAttributes.addAttribute("success", "1");

            return "redirect:/site/home";
        } catch (ServiceException e) {
            redirectAttributes.addAttribute("error", "1");

            return "redirect:/site/home";
        }
    }
}
