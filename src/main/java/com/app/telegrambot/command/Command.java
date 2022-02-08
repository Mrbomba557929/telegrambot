package com.app.telegrambot.command;

import com.app.telegrambot.domain.base.Update;

/**
 * Command interface for handling telegram-bot commands.
 */
public interface Command {

    /**
     * Main method, which is executing command logic.
     *
     * @param update provide {@link Update} object with all the needed data for command.
     */
    void execute(Update update);
}
