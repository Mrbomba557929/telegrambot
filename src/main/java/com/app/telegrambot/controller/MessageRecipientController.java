package com.app.telegrambot.controller;

import com.app.telegrambot.domain.base.response.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MessageRecipientController {

    private final static String COMMAND_PREFIX = "/";

    @PostMapping("${telegrambot.token}")
    public void onUpdateReceived(@NonNull @RequestBody Update update) {
        log.info("Пришёл update: {}", update);

        if (update.hasMessage() && update.message().hasText()) {
            String message = update.message().text().trim();

            if (message.startsWith(COMMAND_PREFIX)) {

            }
        }
    }
}
