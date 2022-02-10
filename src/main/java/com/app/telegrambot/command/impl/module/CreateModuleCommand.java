package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.base.request.SendMessage;
import com.app.telegrambot.domain.base.response.Update;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.fms.State;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.methods.send.MessageSender;
import com.app.telegrambot.service.ModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * {@link Command} for creating the {@link ModuleEntity} entity.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateModuleCommand extends Command {

    private final StateMachine stateMachine;
    private final MessageSender messageSender;
    private final ModuleService moduleService;

    @Override
    public void execute(Update update) {
        try {
            messageSender.sendMessage(SendMessage.builder()
                    .text(format("%s, введите название модуля.", update.message().from().username()))
                    .chatId(update.message().chat().id())
                    .build());

            State state = new State(this::askForModuleName, ModuleEntity.builder());
            stateMachine.transition(update.message().from().id(), state);

        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }

    public void askForModuleName(Update update) {
        String moduleName = update.message().text();
        ModuleEntity savedModule = moduleService.save(moduleName, update.message().from().id());

        try {
            messageSender.sendMessage(SendMessage.builder()
                    .text(format("%s, модуль %s успешно создан.", update.message().from().username(), savedModule.getName()))
                    .chatId(update.message().chat().id())
                    .build());

            stateMachine.remove(update.message().from().id());

        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }
}
