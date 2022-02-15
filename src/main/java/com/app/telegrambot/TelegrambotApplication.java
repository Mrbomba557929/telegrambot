package com.app.telegrambot;

import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@EnableAspectJAutoProxy
public class TelegrambotApplication {

    @Autowired
    private ModuleService moduleService;

    public static void main(String[] args) {
        SpringApplication.run(TelegrambotApplication.class, args);
    }

    public ApplicationRunner applicationRunner() {
        return args -> {
            List<ModuleEntity> page = moduleService.findAll(2, 5).getContent();
            int a = 5;
        };
    }
}
