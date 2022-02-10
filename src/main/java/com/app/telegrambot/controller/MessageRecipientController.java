package com.app.telegrambot.controller;

import com.app.telegrambot.command.CommandContainer;
import com.app.telegrambot.domain.base.response.Update;
import com.app.telegrambot.domain.еnum.CommandName;
import com.app.telegrambot.fms.StateMachine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

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

        if (update.hasMessage() && update.message().hasText()) {
            String message = update.message().text().trim();

            if (message.startsWith(COMMAND_PREFIX)) {

                if (stateMachine.contains(update.message().from().id())) {
                    stateMachine.retrieve(update.message().from().id())
                            .transition()
                            .accept(update);
                } else {
                    commandContainer.retrieve(CommandName.fromText(message)).execute(update);
                }
            }
        }
    }
}
