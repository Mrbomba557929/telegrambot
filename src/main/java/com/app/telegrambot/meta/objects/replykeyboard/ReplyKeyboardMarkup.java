package com.app.telegrambot.meta.objects.replykeyboard;

import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.KeyboardRow;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ReplyKeyboardMarkup implements Keyboard, BotApiObject {

    private static final String KEYBOARD_FIELD = "keyboard";
    private static final String RESIZE_KEYBOARD_FIELD = "resize_keyboard";
    private static final String SELECTIVE_FIELD = "selective";

    @JsonProperty(KEYBOARD_FIELD)
    private List<KeyboardRow> keyboard;

    @JsonProperty(RESIZE_KEYBOARD_FIELD)
    private boolean resizeKeyboard;

    @JsonProperty(SELECTIVE_FIELD)
    private boolean selective;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final List<KeyboardRow> keyboard;

        private boolean resizeKeyboard;
        private boolean selective;

        public Builder() {
            keyboard = new ArrayList<>();
        }

        public Builder resizeKeyboard(boolean resizeKeyboard) {
            this.resizeKeyboard = resizeKeyboard;
            return this;
        }

        public Builder selective(boolean selective) {
            this.selective = selective;
            return this;
        }

        public Builder withRow(KeyboardRow row) {
            keyboard.add(row);
            return this;
        }

        public ReplyKeyboardMarkup build() {
            return new ReplyKeyboardMarkup(keyboard, resizeKeyboard, selective);
        }
    }
}
