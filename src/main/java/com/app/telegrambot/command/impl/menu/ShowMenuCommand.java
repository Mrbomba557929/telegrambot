package com.app.telegrambot.command.impl.menu;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.еnum.ParseMode;
import com.app.telegrambot.meta.methods.send.impl.EditMessageTextSender;
import com.app.telegrambot.meta.methods.send.objects.EditMessageText;
import com.app.telegrambot.meta.methods.send.objects.SendMessage;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
import com.app.telegrambot.meta.objects.replykeyboard.InlineKeyboardMarkup;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.InlineKeyboardButton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {@link Command} shows the main menu.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ShowMenuCommand implements Command {

    public static final String SHOW_MENU_COMMAND_MESSAGE = "Привет, выбери интересующую опцию.";
    public static final String MESSAGE_REGEX = "/menu:previous";

    private final MessageSender sender;
    private final EditMessageTextSender editMessageTextSender;

    @Override
    public void execute(Update update) {
        try {

            if (update.message().text().matches(MESSAGE_REGEX)) {
                editMessageTextSender.send(EditMessageText.builder()
                        .chatId(update.message().chat().id())
                        .messageId(Math.toIntExact(update.message().id()))
                        .text(SHOW_MENU_COMMAND_MESSAGE)
                        .parseMode(ParseMode.MARKDOWN)
                        .replyMarkup(generateKeyboard())
                        .build());
                return;
            }

            sender.send(SendMessage.builder()
                    .text(SHOW_MENU_COMMAND_MESSAGE)
                    .chatId(update.message().chat().id())
                    .replyMarkup(generateKeyboard())
                    .build());

        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при отправке запроса: {}", e.getMessage());
        }
    }

    private InlineKeyboardMarkup generateKeyboard() {
        return InlineKeyboardMarkup.builder()
                .withRow(List.of(
                        InlineKeyboardButton.builder()
                                .text("Найти модуль")
                                .callbackData("/module")
                                .build(),
                        InlineKeyboardButton.builder()
                                .text("Все мои модули")
                                .callbackData("/modules")
                                .build())
                )
                .withRow(List.of(
                        InlineKeyboardButton.builder()
                                .text("Создать модуль")
                                .callbackData("/cm")
                                .build())
                )
                .build();
    }
}
