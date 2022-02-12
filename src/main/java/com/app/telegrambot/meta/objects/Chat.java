package com.app.telegrambot.meta.objects;

import com.app.telegrambot.domain.еnum.ChatType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record Chat(@JsonProperty(ID) Integer id, @JsonProperty(FIRST_NAME) String firstName,
                   @JsonProperty(LAST_NAME) String lastName, @JsonProperty(USERNAME) String username,
                   @JsonProperty(TYPE) ChatType type) {

    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String USERNAME = "username";
    private static final String TYPE = "type";

    @Builder
    public Chat {
    }
}
