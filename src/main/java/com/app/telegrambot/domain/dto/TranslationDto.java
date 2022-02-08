package com.app.telegrambot.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public record TranslationDto(String translation, List<WordDto> words) {

    @Builder
    @JsonCreator
    public TranslationDto(@JsonProperty("translation") String translation, @JsonProperty("words") List<WordDto> words) {
        this.translation = translation;
        this.words = words;
    }
}
