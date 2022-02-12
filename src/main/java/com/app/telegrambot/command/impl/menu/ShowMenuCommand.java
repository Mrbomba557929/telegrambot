package com.app.telegrambot.command.impl.menu;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.meta.methods.send.SendMessage;
import com.app.telegrambot.meta.methods.get.Update;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.methods.send.MessageSender;
import com.app.telegrambot.meta.objects.replykeyboard.InlineKeyboardMarkup;
import com.app.telegrambot.meta.objects.replykeyboard.ReplyKeyboardMarkup;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.InlineKeyboardButton;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.KeyboardRow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ShowMenuCommand implements Command {

    private final MessageSender sender;

    @Override
    public void execute(Update update) {
        try {
            // TODO: сделать inline keyboard вместо reply
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Привет, выбери интересующую опицию.");
            sendMessage.setChatId(update.message().chat().id());

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
            List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
            InlineKeyboardButton button1 = InlineKeyboardButton.builder()
                    .text("Создать модуль")
                    .callbackData("/cm")
                    .build();
            inlineKeyboardButtons.add(button1);
            inlineButtons.add(inlineKeyboardButtons);

            inlineKeyboardMarkup.setInlineKeyboard(inlineButtons);

            sendMessage.setReplyMarkup(inlineKeyboardMarkup);

            sender.sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }
}
