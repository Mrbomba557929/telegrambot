package com.app.telegrambot.controller;

import com.app.telegrambot.command.CommandContainer;
import com.app.telegrambot.context.TelegramBotContextHolder;
import com.app.telegrambot.meta.methods.get.Update;
import com.app.telegrambot.command.CommandName;
import com.app.telegrambot.fms.StateMachine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MessageRecipientController {

    private final static String COMMAND_PREFIX = "/";

    private final StateMachine stateMachine;
    private final CommandContainer commandContainer;

    @PostMapping("${telegrambot.token}")
    public void onUpdateReceived(@NonNull @RequestBody Update update) {
        log.info("Пришёл update: {}", update);

        String message = null;

        if (update.hasCallBackQuery()) {
            message = update.callbackQuery().data();
        } else if (update.hasMessage() && update.message().hasText()) {
            message = update.message().text().trim();
        }

        if (Objects.nonNull(message)) {
            if (message.startsWith(COMMAND_PREFIX)) {
                TelegramBotContextHolder.UPDATE = update;
                commandContainer.retrieve(CommandName.fromText(message))
                        .execute(update);
            } else if (stateMachine.contains(update.message().from().idLong())) {
                stateMachine.retrieve(update.message().from().idLong())
                        .transition()
                        .execute(update);
            }
        }
    }
}
