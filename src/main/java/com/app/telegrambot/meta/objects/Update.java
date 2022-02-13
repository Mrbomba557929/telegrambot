package com.app.telegrambot.meta.objects;

import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Objects;

public record Update(@JsonProperty(UPDATE_ID_FIELD) Long id, @JsonProperty(MESSAGE_FIELD) Message message,
                     @JsonProperty(CALLBACK_QUERY_FIELD) CallbackQuery callbackQuery) implements BotApiObject {

    private static final String UPDATE_ID_FIELD = "update_id";
    private static final String MESSAGE_FIELD = "message";
    private static final String CALLBACK_QUERY_FIELD = "callback_query";

    @Builder
    public Update {
    }

    public boolean hasMessage() {
        return Objects.nonNull(message);
    }

    public boolean hasCallBackQuery() {
        return Objects.nonNull(callbackQuery);
    }
}
