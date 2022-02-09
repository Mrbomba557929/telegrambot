package com.app.telegrambot.mapper.impl;

import com.app.telegrambot.domain.dto.TranslationDto;
import com.app.telegrambot.domain.dto.WordDto;
import com.app.telegrambot.domain.entity.WordEntity;
import com.app.telegrambot.exception.business.NotSupportedException;
import com.app.telegrambot.exception.factory.ExceptionFactory;
import com.app.telegrambot.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

@RequiredArgsConstructor
@Component
public class WordMapper implements Mapper<WordEntity, WordDto> {

    @Override
    public WordEntity toEntity(WordDto dto) {
        throw ExceptionFactory.exceptionBuilder("Error: This method doesn't support!")
                .status(EXPECTATION_FAILED)
                .link("WordMapper/toEntity")
                .build(NotSupportedException.class);
    }

    @Override
    public WordDto toDto(WordEntity entity) {
        List<TranslationDto> translations = entity.getTranslations().stream()
                .map(translation ->
                        TranslationDto.builder()
                                .translation(translation.getTranslation())
                                .build()
                ).toList();

        return WordDto.builder()
                .word(entity.getWord())
                .translations(translations)
                .build();
    }
}
