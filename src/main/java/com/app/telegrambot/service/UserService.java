package com.app.telegrambot.service;

import com.app.telegrambot.meta.objects.User;
import com.app.telegrambot.domain.entity.UserEntity;

/**
 * Interface for the {@link UserEntity} entity.
 */
public interface UserService {

    /**
     * Checks if user exists by id.
     *
     * @param id - the id of the {@link UserEntity}.
     * @return true - exist, false - not exist.
     */
    boolean existsById(Long id);

    /**
     * Method for saving the {@link UserEntity} entity.
     *
     * @param user - the {@link User} entity.
     * @return saved the {@link UserEntity} entity.
     */
    UserEntity save(User user);

    /**
     * Method for finding the {@link UserEntity} by the id.
     *
     * @param id - the id of the {@link UserEntity}.
     * @return found the {@link UserEntity}.
     */
    UserEntity findById(Long id);
}
