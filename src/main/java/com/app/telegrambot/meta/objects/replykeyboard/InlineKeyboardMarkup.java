package com.app.telegrambot.meta.objects.replykeyboard;

import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.InlineKeyboardButton;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class InlineKeyboardMarkup implements Keyboard, BotApiObject {

    private static final String INLINE_KEYBOARD_FIELD = "inline_keyboard";

    @JsonProperty(INLINE_KEYBOARD_FIELD)
    private List<List<InlineKeyboardButton>> inlineKeyboard;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final List<List<InlineKeyboardButton>> inlineKeyboard;

        public Builder() {
            inlineKeyboard = new ArrayList<>();
        }

        public Builder withRow(List<InlineKeyboardButton> buttons) {
            inlineKeyboard.add(buttons);
            return this;
        }

        public InlineKeyboardMarkup build() {
            return new InlineKeyboardMarkup(inlineKeyboard);
        }
    }
}
