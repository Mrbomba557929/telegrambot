package com.app.telegrambot.service.impl;

import com.app.telegrambot.domain.entity.TranslationEntity;
import com.app.telegrambot.exception.runtime.impl.NotFoundException;
import com.app.telegrambot.exception.factory.ExceptionFactory;
import com.app.telegrambot.repository.TranslationRepository;
import com.app.telegrambot.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.app.telegrambot.exception.factory.ExceptionMessage.NOT_FOUND_TRANSLATION;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

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
                        ExceptionFactory.exceptionBuilder(NOT_FOUND_TRANSLATION)
                                .status(EXPECTATION_FAILED)
                                .link("TranslationServiceImpl/findByTranslation")
                                .buildRuntime(NotFoundException.class)
                );
    }
}
