package com.app.telegrambot.domain.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public record Update(Long id, Message message) {

    public Update(@JsonProperty("update_id") Long id, @JsonProperty("message") Message message) {
        this.id = id;
        this.message = message;
    }

    public boolean hasMessage() {
        return Objects.nonNull(message);
    }
}
