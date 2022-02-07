package com.app.telegrambot.controller;

import com.app.telegrambot.domain.module.base.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MessageRecipientController {

    @PostMapping("${telegrambot.token}")
    public void messageRecipient(@RequestBody Update update) {
        log.info("Пришёл update: {}", update);
    }
}
