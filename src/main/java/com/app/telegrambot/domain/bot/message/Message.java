package com.app.telegrambot.domain.bot.message;

import com.app.telegrambot.domain.bot.chat.Chat;
import com.app.telegrambot.domain.bot.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.apache.logging.log4j.util.Strings;

import java.time.Instant;
import java.util.List;

public record Message(Long id, User from, Instant date, Chat chat, String text, List<MessageEntity> entities) {

    @Builder
    @JsonCreator
    public Message(@JsonProperty("message_id") Long id, @JsonProperty("from") User from,
                   @JsonProperty("date") Integer date, @JsonProperty("chat") Chat chat,
                   @JsonProperty("text") String text, @JsonProperty("entities") List<MessageEntity> entities) {
        this(id, from, Instant.ofEpochSecond(date), chat, text, entities);
    }

    public Message(Long id, User from, Instant date, Chat chat, String text, List<MessageEntity> entities) {
        this.id = id;
        this.from = from;
        this.date = date;
        this.chat = chat;
        this.text = text;
        this.entities = entities;
    }

    public boolean hasText() {
        return Strings.isNotBlank(text) && Strings.isNotEmpty(text);
    }
}
