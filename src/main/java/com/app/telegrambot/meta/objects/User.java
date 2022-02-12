package com.app.telegrambot.meta.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record User(Integer id, Boolean isBot, String firstName,
                   String lastName, String username, String languageCode, String fio) {

    private static final String ID_FIELD = "id";
    private static final String IS_BOT_FILED = "is_bot";
    private static final String FIRST_NAME_FIELD = "first_name";
    private static final String LAST_NAME_FIELD = "last_name";
    private static final String USERNAME_FIELD = "username";
    private static final String LANGUAGE_CODE_FIELD = "language_code";

    @Builder
    @JsonCreator
    public User(@JsonProperty(ID_FIELD) Integer id, @JsonProperty(IS_BOT_FILED) Boolean isBot,
                @JsonProperty(FIRST_NAME_FIELD) String firstName, @JsonProperty(LAST_NAME_FIELD) String lastName,
                @JsonProperty(USERNAME_FIELD) String username, @JsonProperty(LANGUAGE_CODE_FIELD) String languageCode) {
        this(id, isBot, firstName, lastName, username, languageCode, String.format("%s %s", firstName, lastName));
    }
}
