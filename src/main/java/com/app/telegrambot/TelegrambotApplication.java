package com.app.telegrambot;

import com.app.telegrambot.domain.entity.TranslationEntity;
import com.app.telegrambot.repository.ModuleRepository;
import com.app.telegrambot.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TelegrambotApplication {

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public static void main(String[] args) {
        SpringApplication.run(TelegrambotApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            TranslationEntity translation = translationRepository.findById("Тест").get();
            int a = 5;
        };
    }
}
