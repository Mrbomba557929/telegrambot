package com.app.telegrambot.command.impl.state;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.meta.methods.send.SendMessage;
import com.app.telegrambot.meta.methods.get.Update;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.meta.methods.send.MessageSender;
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
            SendMessage sendMessage = new SendMessage();

            if (stateMachine.contains(update.message().chat().id())) {
                stateMachine.stop(update.message().chat().id());
                sendMessage.setText(format("%s, состояние успешно остановлено.", update.message().from().fio()));
            } else {
                sendMessage.setText("Дядя, ты что - то перепутал.");
            }

            sendMessage.setChatId(update.message().chat().id());
            sender.sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }
}
