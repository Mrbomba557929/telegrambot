package com.app.telegrambot.command;

import com.app.telegrambot.domain.base.request.SendMessage;
import com.app.telegrambot.domain.base.response.Update;

/**
 * Command abstract class for handling telegram-bot commands.
 */
public abstract class Command implements Dispatched {

    @Override
    public void sendMessage(SendMessage sendMessage) {

    }

    /**
     * Main method, which is executing command logic.
     *
     * @param update provide {@link Update} object with all the needed data for command.
     */
    public abstract void execute(Update update);
}
