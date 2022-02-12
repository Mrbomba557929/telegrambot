package com.app.telegrambot.meta.objects;

import com.app.telegrambot.domain.еnum.MessageEntityType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record MessageEntity(@JsonProperty(OFFSET) Integer offset, @JsonProperty(LENGTH) Integer length,
                            @JsonProperty(TYPE) MessageEntityType type) {

    private static final String OFFSET = "offset";
    private static final String LENGTH = "length";
    private static final String TYPE = "type";

    @Builder
    public MessageEntity {
    }
}
