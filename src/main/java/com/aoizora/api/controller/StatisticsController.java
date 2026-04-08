package com.aoizora.api.controller;

import com.aoizora.api.dto.StatisticsDTO;
import com.aoizora.dao.JdbcReportDAO;
import com.aoizora.dao.domain.LastRegisteredUser;
import com.aoizora.dao.domain.Pet;
import com.aoizora.service.PetService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Locale;

import static com.aoizora.api.dto.StatisticsDTO.StatisticsType.LAST_CREATED_PETS;
import static com.aoizora.api.dto.StatisticsDTO.StatisticsType.LAST_REGISTERED_USERS;

@Controller
@RequestMapping("/site")
public class StatisticsController {

    private final JdbcReportDAO reportDao;
    private final PetService petService;

    public StatisticsController(JdbcReportDAO reportDao, PetService petService) {
        this.reportDao = reportDao;
        this.petService = petService;
    }

    @GetMapping("/information/statistics")
    public String statistics(Locale locale, Model model) {
        StatisticsDTO params = new StatisticsDTO();
        params.setMaxRecordsCount(100);
        params.setType(LAST_REGISTERED_USERS);
        model.addAttribute("statisticsParams, params");

        return "information/statistics";
    }

    @PostMapping("/information/statistics")
    public String statistics(Locale locale, Model model, @Valid @ModelAttribute StatisticsDTO statisticsParams, BindingResult statisticsParamsBindingResult) {

        Iterable<LastRegisteredUser> users = new ArrayList<>();
        Iterable<Pet> pets = new ArrayList<>();
        if (!statisticsParamsBindingResult.hasErrors()) {
            switch (statisticsParams.getType()) {
                case LAST_REGISTERED_USERS:
                    users = reportDao.findLastRegisteredUsers(0, statisticsParams.getMaxRecordsCount());
                    break;
                case LAST_CREATED_PETS:
                    pets = petService.findLastCreatedPets(0, statisticsParams.getMaxRecordsCount());
            }
        }
        model.addAttribute("users", users);
        model.addAttribute("pets", pets);

        return "information/statistics";
    }
}
