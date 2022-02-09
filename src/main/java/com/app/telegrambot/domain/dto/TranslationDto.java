package com.app.telegrambot.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record TranslationDto(String translation) {

    @Builder
    @JsonCreator
    public TranslationDto(@JsonProperty("translation") String translation) {
        this.translation = translation;
    }
}
