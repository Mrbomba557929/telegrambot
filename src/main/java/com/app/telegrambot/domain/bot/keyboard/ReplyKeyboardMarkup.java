package com.app.telegrambot.domain.bot.keyboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReplyKeyboardMarkup extends ReplyKeyboard {

    @JsonProperty("keyboard")
    private final List<KeyboardRow> keyboard;

    @JsonProperty("resize_keyboard")
    private final boolean resizeKeyboard;

    @JsonProperty("selective")
    private final boolean selective;
}
