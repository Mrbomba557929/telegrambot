package com.app.telegrambot.domain.module.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Update(Long id, Message message) {

    public Update(@JsonProperty("update_id") Long id, @JsonProperty("message") Message message) {
        this.id = id;
        this.message = message;
    }
}
