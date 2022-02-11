package com.app.telegrambot;

import com.app.telegrambot.service.ModuleService;
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
    private ModuleService moduleService;

    public static void main(String[] args) {
        SpringApplication.run(TelegrambotApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            int a = 5;
            moduleService.save("fewf", 1);
            int g = 5;
        };
    }
}
