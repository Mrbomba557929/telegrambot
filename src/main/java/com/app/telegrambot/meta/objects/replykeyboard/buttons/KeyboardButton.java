package com.app.telegrambot.meta.objects.replykeyboard.buttons;

import com.app.telegrambot.meta.interfaces.TelegramBot;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record KeyboardButton(@JsonProperty(TEXT_FIELD) String text) implements TelegramBot {

    private static final String TEXT_FIELD = "text";

    @Builder
    public KeyboardButton {
    }
}
