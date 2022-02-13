package com.app.telegrambot.meta.objects;

import com.app.telegrambot.domain.еnum.MessageEntityType;
import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record MessageEntity(@JsonProperty(OFFSET_FIELD) Integer offset, @JsonProperty(LENGTH_FIELD) Integer length,
                            @JsonProperty(TYPE_FIELD) MessageEntityType type) implements BotApiObject {

    private static final String OFFSET_FIELD = "offset";
    private static final String LENGTH_FIELD = "length";
    private static final String TYPE_FIELD = "type";

    @Builder
    public MessageEntity {
    }
}
