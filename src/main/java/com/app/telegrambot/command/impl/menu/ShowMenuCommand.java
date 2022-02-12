package com.app.telegrambot.command.impl.menu;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.bot.request.SendMessage;
import com.app.telegrambot.domain.bot.response.Update;

public class ShowMenuCommand implements Command {

    @Override
    public void execute(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("");
    }
}
