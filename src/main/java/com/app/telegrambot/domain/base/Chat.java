package com.app.telegrambot.domain.base;

import com.app.telegrambot.domain.еnum.ChatType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record Chat(Integer id, String firstName, String lastName, String username, ChatType type) {

    @Builder
    @JsonCreator
    public Chat(@JsonProperty("id") Integer id, @JsonProperty("first_name") String firstName,
                @JsonProperty("last_name") String lastName, @JsonProperty("username") String username,
                @JsonProperty("type") ChatType type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.type = type;
    }
}
