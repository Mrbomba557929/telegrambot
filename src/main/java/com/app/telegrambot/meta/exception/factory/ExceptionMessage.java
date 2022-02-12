package com.app.telegrambot.meta.exception.factory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
    WITHOUT_MESSAGE(""),
    NOT_FOUND_WORD("Sorry, bro, but the word not found."),
    NOT_FOUND_TRANSLATION("Sorry, bro, but the translation not found."),
    NOT_FOUND_MODULE("Sorry, bro, but the module not found."),
    NOT_FOUND_USER("Sorry, bro, but the user not found."),
    ERROR_CREATE_MODULE("You need to write the name of the module."),
    ERROR_IN_THE_COMMAND("You have specified the command parameters incorrect."),
    ERROR_SENDING_MESSAGE("An error occurred while sending the message."),
    IMPOSSIBLE_TO_SAVE_USER("Impossible to save the user!");

    private final String message;
}