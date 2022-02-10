package com.app.telegrambot.command;

import com.app.telegrambot.domain.base.response.Update;
import lombok.extern.slf4j.Slf4j;

/**
 * Command abstract class for handling telegram-bot commands.
 */
@Slf4j
public abstract class Command {

    /**
     * Main method, which is executing command logic.
     *
     * @param update provide {@link Update} object with all the needed data for command.
     */
    public abstract void execute(Update update);
}
