package com.app.telegrambot.service.impl;

import com.app.telegrambot.domain.entity.TranslationEntity;
import com.app.telegrambot.meta.exception.runtime.impl.NotFoundException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.repository.TranslationRepository;
import com.app.telegrambot.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link TranslationService}.
 */
@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;

    @Override
    public TranslationEntity findByTranslation(String translation) {
        return translationRepository.findByTranslation(translation)
                .orElseThrow(() ->
                        ExceptionFactory.exceptionBuilder("Error: translation not found!")
                                .link("TranslationServiceImpl/findByTranslation")
                                .buildRuntime(NotFoundException.class)
                );
    }
}
