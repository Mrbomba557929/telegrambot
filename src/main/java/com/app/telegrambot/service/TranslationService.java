package com.app.telegrambot.service;

import com.app.telegrambot.domain.entity.TranslationEntity;

/**
 * Service for the {@link TranslationEntity}.
 */
public interface TranslationService {

    /**
     * Find the {@link TranslationEntity} by the translation.
     *
     * @param translation - the translation of the {@link TranslationEntity}.
     * @return found the {@link TranslationEntity}.
     */
    TranslationEntity findByTranslation(String translation);
}
