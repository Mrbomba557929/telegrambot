package com.app.telegrambot.meta.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record User(Integer id, Boolean isBot, String firstName,
                   String lastName, String username, String languageCode, String fio) {

    private static final String ID = "id";
    private static final String IS_BOT = "is_bot";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String USERNAME = "username";
    private static final String LANGUAGE_CODE = "language_code";

    @Builder
    @JsonCreator
    public User(@JsonProperty(ID) Integer id, @JsonProperty(IS_BOT) Boolean isBot,
                @JsonProperty(FIRST_NAME) String firstName, @JsonProperty(LAST_NAME) String lastName,
                @JsonProperty(USERNAME) String username, @JsonProperty(LANGUAGE_CODE) String languageCode) {
        this(id, isBot, firstName, lastName, username, languageCode, String.format("%s %s", firstName, lastName));
    }
}
