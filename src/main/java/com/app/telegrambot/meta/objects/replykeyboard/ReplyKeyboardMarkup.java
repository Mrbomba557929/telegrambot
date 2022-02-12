package com.app.telegrambot.meta.objects.replykeyboard;

import com.app.telegrambot.meta.objects.replykeyboard.buttons.KeyboardRow;
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
public class ReplyKeyboardMarkup implements ReplyKeyboard {

    private static final String KEYBOARD_FIELD = "keyboard";
    private static final String RESIZE_KEYBOARD_FIELD = "resize_keyboard";
    private static final String SELECTIVE_FIELD = "selective";

    @JsonProperty(KEYBOARD_FIELD)
    private List<KeyboardRow> keyboard;

    @JsonProperty(RESIZE_KEYBOARD_FIELD)
    private boolean resizeKeyboard;

    @JsonProperty(SELECTIVE_FIELD)
    private boolean selective;
}
