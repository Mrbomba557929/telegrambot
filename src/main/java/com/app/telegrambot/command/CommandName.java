package com.app.telegrambot.command;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CommandName {

    CREATE_MODULE("/cm"),
    STOP_STATE("/stop");

    private final String commandName;

    public static CommandName fromText(String text) {

        if (text != null) {
            for (CommandName provider : CommandName.values()) {
                if (provider.commandName.equalsIgnoreCase(text)) {
                    return provider;
                }
            }
        }

        return null;
    }
}
