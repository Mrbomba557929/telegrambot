package com.app.telegrambot.domain.base.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record SendMessage(@JsonProperty("chat_id") Integer chatId, @JsonProperty("text") String text) {

    @Builder
    public SendMessage {

    }
}
