package com.app.telegrambot.meta.objects.replykeyboard;

import com.app.telegrambot.meta.objects.replykeyboard.buttons.InlineKeyboardButton;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.List;

@JsonDeserialize
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InlineKeyboardMarkup implements Keyboard {

    private static final String INLINE_KEYBOARD_FIELD = "inline_keyboard";

    @JsonProperty(INLINE_KEYBOARD_FIELD)
    private List<List<InlineKeyboardButton>> inlineKeyboard;
}
