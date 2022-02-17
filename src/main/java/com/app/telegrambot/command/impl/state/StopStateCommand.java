package com.app.telegrambot.command.impl.state;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.meta.methods.send.objects.SendMessage;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
import com.app.telegrambot.meta.objects.replykeyboard.InlineKeyboardMarkup;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.InlineKeyboardButton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {@link Command} to stop the current state.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StopStateCommand implements Command {

    public static final String STOP_STATE_COMMAND_MESSAGE_1 = "Диалог остановлен.";
    public static final String STOP_STATE_COMMAND_MESSAGE_2 = "Дядя, ты что - то перепутал.";

    private final StateMachine stateMachine;
    private final MessageSender sender;

    @Override
    public void execute(Update update) {
        try {

            String message;

            if (stateMachine.contains(update.message().from().idLong())) {
                stateMachine.stop(update.message().from().idLong());
                message = STOP_STATE_COMMAND_MESSAGE_1;
            } else {
                message = STOP_STATE_COMMAND_MESSAGE_2;
            }

            sender.send(SendMessage.builder()
                    .text(message)
                    .chatId(update.message().chat().id())
                    .replyMarkup(generateKeyboard())
                    .build());

        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при отправке запроса: {}", e.getMessage());
        }
    }

    private InlineKeyboardMarkup generateKeyboard() {
        return InlineKeyboardMarkup.builder()
                .withRow(
                        List.of(
                                InlineKeyboardButton.builder()
                                        .text("Меню")
                                        .callbackData("/menu:previous")
                                        .build()
                        )
                )
                .build();
    }
}
