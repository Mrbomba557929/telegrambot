package com.app.telegrambot.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

public record ModuleDto(int id, String name, Instant createdAt, List<WordDto> words) {

    @Builder
    @JsonCreator
    public ModuleDto(@JsonProperty("id") int id, @JsonProperty("name") String name,
                     @JsonProperty("createdAt") Instant createdAt, List<WordDto> words) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.words = words;
    }
}
