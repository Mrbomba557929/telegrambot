package com.app.telegrambot.command.impl.state;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.base.request.SendMessage;
import com.app.telegrambot.domain.base.response.Update;
import com.app.telegrambot.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.methods.send.MessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * {@link Command} to stop the current state.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StopStateCommand implements Command {

    private final StateMachine stateMachine;
    private final MessageSender sender;

    @Override
    public void execute(Update update) {
        try {
            if (stateMachine.contains(update.message().chat().id())) {
                stateMachine.stop(update.message().chat().id());

                sender.sendMessage(SendMessage.builder()
                        .text(format("%s, состояние успешно остановлено.", update.message().from().fio()))
                        .chatId(update.message().chat().id())
                        .build());
            } else {
                sender.sendMessage(SendMessage.builder()
                        .text("Дядя, ты что - то перепутал.")
                        .chatId(update.message().chat().id())
                        .build());
            }
        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }
}
