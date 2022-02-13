package com.app.telegrambot.controller;

import com.app.telegrambot.command.CommandContainer;
import com.app.telegrambot.context.TelegramBotContextHolder;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.command.CommandName;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.meta.objects.Message;
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

    public static final String COMMAND_PREFIX = "/";
    public static final String SEPARATOR = ":";

    private final StateMachine stateMachine;
    private final CommandContainer commandContainer;

    @PostMapping("${telegrambot.token}")
    public void onUpdateReceived(@NonNull @RequestBody Update update) {
        log.info("Пришёл update: {}", update);

        if (update.hasCallBackQuery()) {
            update = Update.builder()
                    .id(update.id())
                    .message(Message.builder()
                            .from(update.callbackQuery().from())
                            .chat(update.callbackQuery().message().chat())
                            .text(update.callbackQuery().data().contains(SEPARATOR) ?
                                    update.callbackQuery().data().split(SEPARATOR)[0] :
                                    update.callbackQuery().data())
                            .date(update.callbackQuery().message().date().getNano())
                            .id(update.callbackQuery().message().id())
                            .entities(update.callbackQuery().message().entities())
                            .build())
                    .callbackQuery(update.callbackQuery())
                    .build();
        }

        if (update.hasMessage() && update.message().hasText()) {
            String message = update.message().text().trim();

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
