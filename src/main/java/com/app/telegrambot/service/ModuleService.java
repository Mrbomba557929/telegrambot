package com.app.telegrambot.service;

import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.domain.entity.UserEntity;
import org.springframework.data.domain.Page;

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

    /**
     * Method searches all modules, pagination.
     *
     * @return the list of the {@link ModuleEntity}.
     * @param page - the current page.
     * @param size - number of pages.
     */
    Page<ModuleEntity> findAll(int page, int size);
}
