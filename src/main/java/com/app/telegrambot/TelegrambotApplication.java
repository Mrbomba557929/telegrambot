package com.app.telegrambot;

import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@EnableAspectJAutoProxy
public class TelegrambotApplication {

    @Autowired
    private ModuleRepository moduleService;

    public static void main(String[] args) {
        SpringApplication.run(TelegrambotApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            Iterable<ModuleEntity> moduleEntity = moduleService.findAll();
            int a = 5;
        };
    }
}
