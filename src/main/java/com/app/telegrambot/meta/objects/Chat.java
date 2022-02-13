package com.app.telegrambot.meta.objects;

import com.app.telegrambot.domain.еnum.ChatType;
import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record Chat(@JsonProperty(ID_FIELD) Integer id, @JsonProperty(FIRST_NAME_FIELD) String firstName,
                   @JsonProperty(LAST_NAME_FIELD) String lastName, @JsonProperty(USERNAME_FIELD) String username,
                   @JsonProperty(TYPE_FIELD) ChatType type) implements BotApiObject {

    private static final String ID_FIELD = "id";
    private static final String FIRST_NAME_FIELD = "first_name";
    private static final String LAST_NAME_FIELD = "last_name";
    private static final String USERNAME_FIELD = "username";
    private static final String TYPE_FIELD = "type";

    @Builder
    public Chat {
    }
}
