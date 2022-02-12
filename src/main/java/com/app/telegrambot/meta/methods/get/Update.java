package com.app.telegrambot.meta.methods.get;

import com.app.telegrambot.meta.objects.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public record Update(@JsonProperty(UPDATE_ID) Long id, @JsonProperty(MESSAGE) Message message) {

    private static final String UPDATE_ID = "update_id";
    private static final String MESSAGE = "message";

    public boolean hasMessage() {
        return Objects.nonNull(message);
    }
}
