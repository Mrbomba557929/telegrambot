package com.app.telegrambot.meta.objects;

import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.apache.logging.log4j.util.Strings;

import java.time.Instant;
import java.util.List;

public record Message(Long id, User from, Instant date, Chat chat, String text, List<MessageEntity> entities)
        implements BotApiObject {

    private static final String MESSAGE_ID_FIELD = "message_id";
    private static final String FROM_FIELD = "from";
    private static final String DATE_FIELD = "date";
    private static final String CHAT_FIELD = "chat";
    private static final String TEXT_FIELD = "text";
    private static final String ENTITIES_FIELD = "entities";

    @Builder
    @JsonCreator
    public Message(@JsonProperty(MESSAGE_ID_FIELD) Long id, @JsonProperty(FROM_FIELD) User from,
                   @JsonProperty(DATE_FIELD) Integer date, @JsonProperty(CHAT_FIELD) Chat chat,
                   @JsonProperty(TEXT_FIELD) String text, @JsonProperty(ENTITIES_FIELD) List<MessageEntity> entities) {
        this(id, from, Instant.ofEpochSecond(date), chat, text, entities);
    }

    public boolean hasText() {
        return Strings.isNotBlank(text) && Strings.isNotEmpty(text);
    }
}
