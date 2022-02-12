package com.app.telegrambot.meta.methods.get;

import com.app.telegrambot.meta.objects.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public record Update(@JsonProperty(UPDATE_ID_FIELD) Long id, @JsonProperty(MESSAGE_FIELD) Message message) {

    private static final String UPDATE_ID_FIELD = "update_id";
    private static final String MESSAGE_FIELD = "message";

    public boolean hasMessage() {
        return Objects.nonNull(message);
    }
}
