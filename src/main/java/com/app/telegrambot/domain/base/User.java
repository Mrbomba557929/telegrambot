package com.app.telegrambot.domain.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record User(Integer id, Boolean isBot, String firstName,
                   String lastName, String username, String languageCode) {

    @Builder
    @JsonCreator
    public User(@JsonProperty("id") Integer id, @JsonProperty("is_bot") Boolean isBot,
                @JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName,
                @JsonProperty("username") String username, @JsonProperty("language_code") String languageCode) {
        this.id = id;
        this.isBot = isBot;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.languageCode = languageCode;
    }
}