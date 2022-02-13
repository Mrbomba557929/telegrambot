package com.app.telegrambot.meta.methods.get;

import com.app.telegrambot.meta.objects.CallbackQuery;
import com.app.telegrambot.meta.objects.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Objects;

public record Update(@JsonProperty(UPDATE_ID_FIELD) Long id, @JsonProperty(MESSAGE_FIELD) Message message,
                     @JsonProperty(CALLBACK_QUERY_FIELD) CallbackQuery callbackQuery) {

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
