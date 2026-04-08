package com.aoizora.api.controller;

import com.aoizora.api.dto.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/site")
public class ServerController {

    private static final Logger logger = LoggerFactory.getLogger(ServerController.class);

    @GetMapping("/information/serverInfo")
    public String serverInfo(Locale locale, Model model) {
        logger.info("Server info. The client locale is {}.", locale);

        String[] propertyNames = { "java.version", "java.vendor", "os.name", "os.arch", "os.version" };
        List<ServerInfo> infos = Arrays.stream(propertyNames)
                .map(key -> new ServerInfo(key, System.getProperty(key)))
                .toList();
        model.addAttribute("infos", infos);

        return "information/server";
    }
}
