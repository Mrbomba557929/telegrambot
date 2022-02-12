package com.app.telegrambot.meta.objects.replykeyboard.buttons;

import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiValidationException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.meta.interfaces.Validable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import lombok.Builder;

import static com.app.telegrambot.meta.exception.factory.ExceptionMessage.WITHOUT_MESSAGE;

public record KeyboardButton(@JsonProperty(TEXT) String text) implements Validable {

    private static final String TEXT = "text";

    @Builder
    public KeyboardButton {
    }

    @Override
    public void validate() throws TelegramApiValidationException {
        if (Strings.isNullOrEmpty(text)) {
            throw ExceptionFactory.exceptionBuilder(WITHOUT_MESSAGE)
                    .link("KeyboardButton/validate")
                    .buildCompileTime(TelegramApiValidationException.class);
        }
    }
}
