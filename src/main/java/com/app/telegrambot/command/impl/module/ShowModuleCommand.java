package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.domain.еnum.ParseMode;
import com.app.telegrambot.fms.State;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.methods.send.impl.EditMessageTextSender;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
import com.app.telegrambot.meta.methods.send.objects.EditMessageText;
import com.app.telegrambot.meta.methods.send.objects.SendMessage;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.service.ModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ShowModuleCommand implements Command {

    public static final String DELIMITER = ":";
    public static final String DOT = "·";
    public static final String ASK_FOR_NAME_MODULE = "Назови имя модуля";

    private final ModuleService moduleService;
    private final EditMessageTextSender editMessageTextSender;
    private final MessageSender messageSender;
    private final StateMachine stateMachine;

    @Override
    public void execute(Update update) {
        try {

            if (update.hasCallBackQuery()) {
                ModuleEntity module = moduleService.findById(Long.parseLong(update.message().text().split(DELIMITER)[1]));
                editMessageTextSender.send(EditMessageText.builder()
                        .chatId(update.message().chat().id())
                        .messageId(Math.toIntExact(update.message().id()))
                        .parseMode(ParseMode.MARKDOWN)
                        .text(DOT + module.toString() + DOT)
                        .replyMarkup(update.message().replyMarkup())
                        .build());
                return;
            }

            messageSender.send(SendMessage.builder()
                    .text(ASK_FOR_NAME_MODULE)
                    .parseMode(ParseMode.MARKDOWN)
                    .chatId(update.message().chat().id())
                    .build());

            stateMachine.addState(update.message().from().idLong(), State.builder()
                    .transition(this::askForNameModule)
                    .build());

        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }

    private void askForNameModule(Update update) {
        try {
            ModuleEntity module = moduleService.findByNameAndUserId(update.message().text(), update.message().from().idLong());

            messageSender.send(SendMessage.builder()
                    .text(module.toString())
                    .chatId(update.message().chat().id())
                    .parseMode(ParseMode.MARKDOWN)
                    .build());

            stateMachine.stop(update.message().from().idLong());
        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }
}
