package com.app.telegrambot.domain.bot.keyboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReplyKeyboardMarkup extends ReplyKeyboard {

    @JsonProperty("keyboard")
    private List<KeyboardRow> keyboard;

    @JsonProperty("resize_keyboard")
    private boolean resizeKeyboard;

    @JsonProperty("selective")
    private boolean selective;
}
