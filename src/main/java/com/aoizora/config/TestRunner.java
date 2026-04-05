package com.aoizora.config;

import com.aoizora.dao.LevelDao;
import com.aoizora.dao.domain.Level;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TestRunner {

    private final LevelDao dao;

    public TestRunner(LevelDao dao) {
        this.dao = dao;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Optional<Level> opt = dao.findById(1);
            opt.ifPresent(System.out::println);
        };
    }
}
