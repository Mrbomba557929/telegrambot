package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.meta.methods.send.SendMessage;
import com.app.telegrambot.meta.methods.get.Update;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.fms.State;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.meta.methods.send.MessageSender;
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
public class CreateModuleCommand implements Command {

    private final StateMachine stateMachine;
    private final MessageSender messageSender;
    private final ModuleService moduleService;

    @Override
    public void execute(Update update) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(format("%s, введите название модуля.", update.message().from().fio()));
            sendMessage.setChatId(update.message().chat().id());
            messageSender.sendMessage(sendMessage);

            stateMachine.start(update.message().from().idLong(), State.create(this::askForModuleName, ModuleEntity.builder()));
        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }

    public void askForModuleName(Update update) {
        try {
            log.info("Начало работы askForModuleName метода. Имя модуля: {}", update.message().text());

            ModuleEntity savedModule = moduleService.save(update.message().text(), update.message().from().idLong());

            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(format("%s, модуль '%s' успешно создан.", update.message().from().fio(), savedModule.getName()));
            sendMessage.setChatId(update.message().chat().id());
            messageSender.sendMessage(sendMessage);

            stateMachine.stop(update.message().from().idLong());
        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }
}
