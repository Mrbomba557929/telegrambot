package com.app.telegrambot.command.impl;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
import com.app.telegrambot.meta.methods.send.SendMessage;
import com.app.telegrambot.meta.objects.replykeyboard.InlineKeyboardMarkup;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.InlineKeyboardButton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The {@link Command} is needed to send an error to the user.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UnknownCommand implements Command {

    public static final String UNKNOWN_COMMAND_MESSAGE = "Чел, ты ввёл неизвестную команду, нажми на 'Меню'";

    private final MessageSender sender;

    @Override
    public void execute(Update update) {
        try {
            sender.send(SendMessage.builder()
                    .text(UNKNOWN_COMMAND_MESSAGE)
                    .chatId(update.message().chat().id())
                    .replyMarkup(InlineKeyboardMarkup.builder()
                            .withRow(
                                    List.of(
                                            InlineKeyboardButton.builder()
                                                    .text("Меню")
                                                    .callbackData("/menu")
                                                    .build()
                                    )
                            )
                            .build())
                    .build());
        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }
}
