package com.app.telegrambot.mapper.impl;

import com.app.telegrambot.domain.dto.TranslationDto;
import com.app.telegrambot.domain.entity.TranslationEntity;
import com.app.telegrambot.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class TranslationMapper implements Mapper<TranslationEntity, TranslationDto> {

    @Override
    public TranslationEntity toEntity(TranslationDto dto) {
        return TranslationEntity.builder()
                .translation(dto.translation())
                .build();
    }

    @Override
    public TranslationDto toDto(TranslationEntity entity) {
        return TranslationDto.builder()
                .translation(entity.getTranslation())
                .build();
    }
}
