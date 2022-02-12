package com.app.telegrambot.meta.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CallbackQuery(@JsonProperty(ID_FIELD) String id, @JsonProperty(FROM_FIELD) User from,
                            @JsonProperty(MESSAGE_FIELD) Message message, @JsonProperty(DATA_FIELD) String data) {

    private static final String ID_FIELD = "id";
    private static final String FROM_FIELD = "from";
    private static final String MESSAGE_FIELD = "message";
    private static final String DATA_FIELD = "data";
}
