package com.app.telegrambot;

import com.app.telegrambot.controller.MessageRecipientController;
import com.app.telegrambot.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@EnableAspectJAutoProxy
public class TelegrambotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegrambotApplication.class, args);
    }
}
