package com.app.telegrambot.mapper.impl;

import com.app.telegrambot.domain.dto.ModuleDto;
import com.app.telegrambot.domain.dto.WordDto;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.domain.entity.WordEntity;
import com.app.telegrambot.exception.runtime.impl.NotSupportedException;
import com.app.telegrambot.exception.factory.ExceptionFactory;
import com.app.telegrambot.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

/**
 * Implementation of the {@link Mapper}.
 */
@Component
@RequiredArgsConstructor
public class ModuleMapper implements Mapper<ModuleEntity, ModuleDto> {

    private final Mapper<WordEntity, WordDto> wordMapper;

    @Override
    public ModuleEntity toEntity(ModuleDto dto) {
        throw ExceptionFactory.exceptionBuilder("Error: This method doesn't support!")
                .status(EXPECTATION_FAILED)
                .link("ModuleMapper/toEntity")
                .buildRuntime(NotSupportedException.class);
    }

    @Override
    public ModuleDto toDto(ModuleEntity entity) {
        return ModuleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .words(null)
                .build();
    }
}
