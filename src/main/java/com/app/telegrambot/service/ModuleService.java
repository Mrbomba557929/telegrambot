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

    /**
     * Method searches one the {@link ModuleEntity} by the id.
     *
     * @param id of the {@link ModuleEntity}.
     * @return found the {@link ModuleEntity}.
     */
    ModuleEntity findById(Long id);

    /**
     * Method searches one the {@link ModuleEntity} by the name and the {@link UserEntity} id.
     *
     * @param name of the {@link ModuleEntity}.
     * @param id of the {@link UserEntity}.
     * @return found the {@link ModuleEntity}.
     */
    ModuleEntity findByNameAndUserId(String name, Long id);

    /**
     * Method deletes the {@link ModuleEntity} by the name and the {@link UserEntity} id.
     *
     * @param name of the {@link ModuleEntity}.
     * @param id of the {@link UserEntity}.
     */
    void deleteByNameAndUserId(String name, Long id);

    /**
     * Method checks exists the {@link ModuleEntity} by the name and the {@link UserEntity} id.
     *
     * @param name of the {@link ModuleEntity}.
     * @param id of the {@link UserEntity}.
     * @return true or false.
     */
    Boolean existsByNameAndUserId(String name, Long id);
}
