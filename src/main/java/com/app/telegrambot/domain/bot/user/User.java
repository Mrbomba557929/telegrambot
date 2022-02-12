package com.app.telegrambot.domain.bot.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record User(Integer id, Boolean isBot, String firstName,
                   String lastName, String username, String languageCode, String fio) {

    @Builder
    @JsonCreator
    public User(@JsonProperty("id") Integer id, @JsonProperty("is_bot") Boolean isBot,
                @JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName,
                @JsonProperty("username") String username, @JsonProperty("language_code") String languageCode) {
        this(id, isBot, firstName, lastName, username, languageCode, String.format("%s %s", firstName, lastName));
    }
}
