package com.app.telegrambot.service;

import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.domain.entity.UserEntity;

/**
 * Service for the {@link ModuleEntity} entity.
 */
public interface ModuleService {

    /**
     * Saves the {@link ModuleEntity} to the database.
     *
     * @param name - the name of the {@link ModuleEntity}.
     * @param userId - the id of the {@link UserEntity}.
     * @return saved the {@link ModuleEntity}.
     */
    ModuleEntity save(String name, Long userId);
}
