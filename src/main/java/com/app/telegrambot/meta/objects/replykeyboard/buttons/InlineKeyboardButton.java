package com.app.telegrambot.meta.objects.replykeyboard.buttons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record InlineKeyboardButton (@JsonProperty(TEXT_FIELD) String text, @JsonProperty(URL_FIELD) String url,
                                    @JsonProperty(CALLBACK_DATA_FIELD) String callbackData) {

    private static final String TEXT_FIELD = "text";
    private static final String URL_FIELD = "url";
    private static final String CALLBACK_DATA_FIELD = "callback_data";

    @Builder
    public InlineKeyboardButton {
    }
}
