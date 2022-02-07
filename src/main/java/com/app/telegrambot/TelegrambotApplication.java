package com.app.telegrambot;

import com.app.telegrambot.domain.module.entity.WordEntity;
import com.app.telegrambot.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TelegrambotApplication {

    @Autowired
    private WordRepository wordRepository;

    public static void main(String[] args) {
        SpringApplication.run(TelegrambotApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            WordEntity wordEntities = wordRepository.findByWord("Test");
            int a = 5;
        };
    }
}
