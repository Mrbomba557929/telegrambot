package com.app.telegrambot.mapper.impl;

import com.app.telegrambot.domain.dto.TranslationDto;
import com.app.telegrambot.domain.dto.WordDto;
import com.app.telegrambot.domain.entity.WordEntity;
import com.app.telegrambot.domain.entity.ref.WordTranslationRef;
import com.app.telegrambot.mapper.Mapper;
import com.app.telegrambot.service.TranslationService;
import com.app.telegrambot.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class WordMapper implements Mapper<WordEntity, WordDto> {

    private final WordService wordService;
    private final TranslationService translationService;

    @Override
    public WordEntity toEntity(WordDto dto) {
        // TODO: ИСПРАВИТЬ ВСЕ MAPPERS
        Set<WordTranslationRef> refs = new HashSet<>();

        if (Objects.nonNull(dto.translates()) && dto.translates().size() > 0) {
            refs = wordService.findByWord(dto.word()).getTranslations();
        }

        return WordEntity.builder()
                .word(dto.word())
                .translations(refs)
                .build();
    }

    @Override
    public WordDto toDto(WordEntity entity) {
        List<TranslationDto> translations = entity.getTranslations().stream()
                .map(translation -> translationService.findByTranslation(translation.getTranslation()))
                .map(translationEntity ->
                        TranslationDto.builder()
                                .translation(translationEntity.getTranslation())
                                .build()
                ).toList();

        return WordDto.builder()
                .word(entity.getWord())
                .translates(translations)
                .build();
    }
}
