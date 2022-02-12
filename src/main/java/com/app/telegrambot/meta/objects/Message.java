package com.app.telegrambot.meta.objects;

import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiValidationException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.meta.interfaces.Validable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.apache.logging.log4j.util.Strings;

import java.time.Instant;
import java.util.List;

import static com.app.telegrambot.meta.exception.factory.ExceptionMessage.WITHOUT_MESSAGE;

public record Message(Long id, User from, Instant date, Chat chat, String text, List<MessageEntity> entities)
        implements Validable {

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

    @Override
    public void validate() throws TelegramApiValidationException {
        if (com.google.common.base.Strings.isNullOrEmpty(text)) {
            throw ExceptionFactory.exceptionBuilder(WITHOUT_MESSAGE)
                    .link("Message/validate")
                    .buildCompileTime(TelegramApiValidationException.class);
        }
    }
}
