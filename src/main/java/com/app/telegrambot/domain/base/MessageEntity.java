package com.app.telegrambot.domain.base;

import com.app.telegrambot.domain.еnum.MessageEntityType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record MessageEntity(Integer offset, Integer length, MessageEntityType type) {

    @Builder
    @JsonCreator
    public MessageEntity(@JsonProperty("offset") Integer offset, @JsonProperty("length") Integer length,
                         @JsonProperty("type") MessageEntityType type) {
        this.offset = offset;
        this.length = length;
        this.type = type;
    }
}
