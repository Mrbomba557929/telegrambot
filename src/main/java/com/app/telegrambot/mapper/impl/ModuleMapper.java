package com.app.telegrambot.mapper.impl;

import com.app.telegrambot.domain.dto.ModuleDto;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.mapper.Mapper;
import org.springframework.stereotype.Component;

/**
 * Implementation of the {@link Mapper}.
 */
@Component
public class ModuleMapper implements Mapper<ModuleEntity, ModuleDto> {

    @Override
    public ModuleEntity toEntity(ModuleDto dto) {
        // TODO: ИСПРАВИТЬ ВСЕ MAPPERS
        return ModuleEntity.builder()
                .id(dto.id())
                .name(dto.name())
                .createdAt(dto.createdAt())
                .words(null)
                .build();
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
