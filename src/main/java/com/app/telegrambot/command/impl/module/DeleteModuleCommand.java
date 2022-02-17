package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.еnum.ParseMode;
import com.app.telegrambot.fms.State;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
import com.app.telegrambot.meta.methods.send.objects.SendMessage;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.service.ModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteModuleCommand implements Command {

    public static final String DELETE_MODULE_MESSAGE = "Введите название модуля, который нужно удалить.";
    public static final String MESSAGE_AFTER_REMOVING_MODULE = "Модуль '%s' успешно удален.";
    public static final String ERROR_MESSAGE = "Модуль '%s' не найдел. Убедись, что ты правильно написал название.";

    private final StateMachine stateMachine;
    private final MessageSender messageSender;
    private final ModuleService moduleService;

    @Override
    public void execute(Update update) {
        try {

            messageSender.send(SendMessage.builder()
                    .text(DELETE_MODULE_MESSAGE)
                    .chatId(update.message().chat().id())
                    .parseMode(ParseMode.MARKDOWN)
                    .build());

            stateMachine.addState(update.message().from().idLong(), State.builder()
                    .transition(this::askForModuleName)
                    .build());

        } catch (TelegramApiException e) {
            log.info("Произошла ошибка при отправке сообщения: {}", e.getMessage());
        }
    }

    private void askForModuleName(Update update) {
        try {

            String moduleName = update.message().text();
            Long userId = update.message().from().idLong();

            String text;

            if (moduleService.existsByNameAndUserId(moduleName, userId)) {
                moduleService.deleteByNameAndUserId(moduleName, userId);
                text = MESSAGE_AFTER_REMOVING_MODULE;
            } else {
                text = ERROR_MESSAGE;
            }

            messageSender.send(SendMessage.builder()
                    .text(format(text, update.message().text()))
                    .chatId(update.message().chat().id())
                    .parseMode(ParseMode.MARKDOWN)
                    .build());

            stateMachine.stop(userId);

        } catch (TelegramApiException e) {
            log.info("Произошла ошибка при отправки сообщения: {}", e.getMessage());
        }
    }
}
