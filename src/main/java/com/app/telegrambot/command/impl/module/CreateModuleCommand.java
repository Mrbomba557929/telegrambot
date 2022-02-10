package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.base.request.SendMessage;
import com.app.telegrambot.domain.base.response.Update;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.fms.State;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.methods.send.MessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * {@link Command} for creating the {@link ModuleEntity} entity.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateModuleCommand extends Command {

    private final StateMachine stateMachine;
    private final MessageSender messageSender;

    @Override
    public void execute(Update update) {
        String message =
                """
                %s, введите название модуля.
                """.formatted(update.message().from().username());

        try {
            messageSender.sendMessage(SendMessage.builder()
                    .text(message)
                    .chatId(update.message().chat().id())
                    .build());

            State state = new State(this::askForModuleName, ModuleEntity.builder());
            stateMachine.transition(update.message().chat().id(), state);

        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }

    public void askForModuleName(Update update) {
        String message =
                """
                %s, модуль %s успешно создан.
                """.formatted(update.message().from().username(), update.message().text());



    }
}
