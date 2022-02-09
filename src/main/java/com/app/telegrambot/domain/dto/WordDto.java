package com.app.telegrambot.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public record WordDto(String word, List<TranslationDto> translations) {

    @Builder
    @JsonCreator
    public WordDto(@JsonProperty("word") String word, @JsonProperty("translates") List<TranslationDto> translations) {
        this.word = word;
        this.translations = translations;
    }
}
