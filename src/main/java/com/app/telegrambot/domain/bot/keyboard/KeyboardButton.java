package com.app.telegrambot.domain.bot.keyboard;

import lombok.Builder;

public record KeyboardButton(String text) {

    @Builder
    public KeyboardButton {
    }
}
