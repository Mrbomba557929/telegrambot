package com.app.telegrambot.command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CommandName {

    UNKNOWN_COMMAND("/unknown"),
    CREATE_MODULE("/cm"),
    MODULES("/modules"),
    SHOW_MODULE("/module"),
    STOP_STATE("/stop"),
    SHOW_MENU("/menu"),
    DELETE_MODULE("/deleteModule"),
    START("/start"),
    ADD_WORD("/addWords"),
    SHOW_ALL_WORDS("/showWords");

    private static final String REGEX = "/[a-zA-Z]+(:.+)+";
    private static final String DELIMITER = ":";

    private final String commandName;

    /**
     * Method allows you to convert from the {@link String} to the {@link CommandName}.
     *
     * @param text which will be converted
     * @return the {@link CommandName}.
     */
    public static CommandName fromText(String text) {

        if (text.matches(REGEX)) {
            text = text.split(DELIMITER)[0];
        }

        for (CommandName provider : CommandName.values()) {
            if (provider.commandName.equalsIgnoreCase(text)) {
                return provider;
            }
        }

        return UNKNOWN_COMMAND;
    }
}
