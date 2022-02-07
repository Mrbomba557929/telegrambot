package com.app.telegrambot.domain.module.еnum;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChatType {
    @JsonProperty("private") PRIVATE,
    @JsonProperty("group") GROUP,
    @JsonProperty("supergroup") SUPERGROUP,
    @JsonProperty("channel") CHANNEL
}
