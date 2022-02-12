package com.app.telegrambot.command.impl.menu;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.meta.methods.send.SendMessage;
import com.app.telegrambot.meta.methods.get.Update;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.methods.send.MessageSender;
import com.app.telegrambot.meta.objects.replykeyboard.ReplyKeyboardMarkup;
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
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Hello, please select one option.");
            sendMessage.setChatId(update.message().chat().id());

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

            List<KeyboardRow> keyboardRows = new ArrayList<>();

            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add("all modules");

            keyboardRows.add(keyboardRow);

            replyKeyboardMarkup.setKeyboard(keyboardRows);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setSelective(true);

            sendMessage.setReplyMarkup(replyKeyboardMarkup);

            sender.sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }
}
