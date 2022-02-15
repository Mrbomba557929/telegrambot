package com.app.telegrambot.command.impl.menu;

import com.app.telegrambot.command.Command;
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

    public static final String SHOW_MENU_COMMAND_MESSAGE = "Привет, выбери интересующую опицию.";

    private final MessageSender sender;

    @Override
    public void execute(Update update) {
        try {
            sender.send(SendMessage.builder()
                    .text(SHOW_MENU_COMMAND_MESSAGE)
                    .chatId(update.message().chat().id())
                    .replyMarkup(InlineKeyboardMarkup.builder()
                            .withRow(
                                    List.of(
                                            InlineKeyboardButton.builder()
                                                    .text("Создать модуль")
                                                    .callbackData("/cm")
                                                    .build(),
                                            InlineKeyboardButton.builder()
                                                    .text("Мои модули")
                                                    .callbackData("/modules")
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
