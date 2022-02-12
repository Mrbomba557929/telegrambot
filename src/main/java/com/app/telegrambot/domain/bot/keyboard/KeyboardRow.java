package com.app.telegrambot.domain.bot.keyboard;

import java.util.ArrayList;
import java.util.List;

public class KeyboardRow {

    private final List<KeyboardButton> keyboardButtons;

    public KeyboardRow() {
        keyboardButtons = new ArrayList<>();
    }

    public boolean add(String text) {
        keyboardButtons.add(KeyboardButton.builder()
                .text(text)
                .build());

        return true;
    }

    public boolean add(int index, String text) {

        if (index < 0 || index >= keyboardButtons.size()) {
            return false;
        }

        keyboardButtons.add(KeyboardButton.builder()
                .text(text)
                .build());

        return true;
    }

    public boolean add(KeyboardButton button) {
        keyboardButtons.add(button);
        return true;
    }

    public boolean add(int index, KeyboardButton button) {

        if (index < 0 || index >= keyboardButtons.size()) {
            return false;
        }

        keyboardButtons.add(button);
        return true;
    }

    public boolean contains(String text) {
        return keyboardButtons.contains(KeyboardButton.builder()
                .text(text)
                .build());
    }

    public int indexOf(String text) {
        return keyboardButtons.indexOf(KeyboardButton.builder()
                .text(text)
                .build());
    }
}
