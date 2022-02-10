package com.app.telegrambot.service.impl;

import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.repository.ModuleRepository;
import com.app.telegrambot.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Implementation of the {@link ModuleService}.
 */
@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    @Override
    public ModuleEntity save(String name, Integer userId) {
        return moduleRepository.save(name, Instant.now(), userId);
    }
}
