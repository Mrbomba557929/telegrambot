package com.app.telegrambot.service;

import com.app.telegrambot.domain.entity.WordEntity;

/**
 * Service for the {@link WordEntity}.
 */
public interface WordService {

    /**
     * Find the {@link WordEntity} by the word.
     *
     * @param word - the word of the {@link WordEntity}.
     * @return found the {@link WordEntity}.
     */
    WordEntity findByWord(String word);

    /**
     * Method for saving the {@link WordEntity}.
     *
     * @param word - the word of the {@link WordEntity}.
     * @return saved the {@link WordEntity}.
     */
    WordEntity save(WordEntity word);
}
