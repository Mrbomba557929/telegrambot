package com.app.telegrambot.command.impl;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.еnum.ParseMode;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
import com.app.telegrambot.meta.methods.send.objects.SendMessage;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.meta.objects.replykeyboard.ReplyKeyboardMarkup;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.KeyboardButton;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.KeyboardRow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class StartCommand implements Command {

    public static final String START_COMMAND_MESSAGE =
            """
            Привет! Этот бот разработан и поддерживается Артёмом Ширкуновым.
            Бот предназначен для изучения слов.
            Вы можете создавать модули, в которые записывать слово и перевод его на любом языке.
            """;

    private final MessageSender messageSender;

    @Override
    public void execute(Update update) {
        try {

            messageSender.send(SendMessage.builder()
                    .text(START_COMMAND_MESSAGE)
                    .chatId(update.message().chat().id())
                    .replyMarkup(generateReplyKeyboard())
                    .parseMode(ParseMode.MARKDOWN)
                    .build());

        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при отправке запроса: {}", e.getMessage());
        }
    }

    private ReplyKeyboardMarkup generateReplyKeyboard() {
        return ReplyKeyboardMarkup.builder()
                .withRow(KeyboardRow.of(
                        KeyboardButton.builder()
                                .text("/menu")
                                .build()
                ))
                .build();
    }
}
