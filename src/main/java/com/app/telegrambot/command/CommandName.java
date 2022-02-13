package com.app.telegrambot.command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CommandName {

    UNKNOWN_COMMAND("/unknown"),
    CREATE_MODULE("/cm"),
    MODULES("/m"),
    STOP_STATE("/stop"),
    SHOW_MENU("/menu");

    private final String commandName;

    /**
     * Method allows you to convert from the {@link String} to the {@link CommandName}.
     *
     * @param text which will be converted
     * @return the {@link CommandName}.
     */
    public static CommandName fromText(String text) {

        if (text != null) {
            for (CommandName provider : CommandName.values()) {
                if (provider.commandName.equalsIgnoreCase(text)) {
                    return provider;
                }
            }
        }

        return UNKNOWN_COMMAND;
    }
}
