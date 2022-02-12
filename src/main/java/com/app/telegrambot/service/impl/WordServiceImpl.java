package com.app.telegrambot.service.impl;

import com.app.telegrambot.domain.entity.WordEntity;
import com.app.telegrambot.meta.exception.runtime.impl.NotFoundException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.repository.WordRepository;
import com.app.telegrambot.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

/**
 * Implementation of the {@link WordService}.
 */
@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    @Override
    public WordEntity findByWord(String word) {
        return wordRepository.findByWord(word)
                .orElseThrow(() ->
                        ExceptionFactory.exceptionBuilder("Error: word not found!")
                                .status(EXPECTATION_FAILED)
                                .link("WordServiceImpl/findByWord")
                                .buildRuntime(NotFoundException.class)
                );
    }
}
