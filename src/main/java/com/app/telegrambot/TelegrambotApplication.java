package com.app.telegrambot;

import com.app.telegrambot.controller.MessageRecipientController;
import com.app.telegrambot.domain.bot.chat.Chat;
import com.app.telegrambot.domain.bot.message.Message;
import com.app.telegrambot.domain.bot.user.User;
import com.app.telegrambot.domain.bot.response.Update;
import com.app.telegrambot.domain.еnum.ChatType;
import com.app.telegrambot.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.Instant;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@EnableAspectJAutoProxy
public class TelegrambotApplication {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private MessageRecipientController recipientController;

    public static void main(String[] args) {
        SpringApplication.run(TelegrambotApplication.class, args);
    }


    public ApplicationRunner applicationRunner() {
        return args -> {
            Update update = new Update(
                    1L,
                    new Message(
                            1L,
                            new User(
                                    1,
                                    false,
                                    "32",
                                    "4343",
                                    "trgtr",
                                    "eu"
                            ),
                            Instant.now(),
                            new Chat(1, "fewfew", "fewfew", "fewfewfew", ChatType.CHANNEL),
                            "/cm",
                            null));
            recipientController.onUpdateReceived(update);
        };
    }
}
