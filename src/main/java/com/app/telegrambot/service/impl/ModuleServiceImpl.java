package com.app.telegrambot.service.impl;

import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.exception.factory.ExceptionFactory;
import com.app.telegrambot.exception.runtime.impl.ImpossibleToSaveException;
import com.app.telegrambot.repository.ModuleRepository;
import com.app.telegrambot.service.ModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

/**
 * Implementation of the {@link ModuleService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    @Override
    public ModuleEntity save(String name, Integer userId) {
        try {
            return moduleRepository.save(name, Instant.now(), userId);
        } catch (DataAccessException e) {
            log.error("Impossible to save the user. {}", e.getMessage());
            throw ExceptionFactory.exceptionBuilder(e.getMessage())
                    .status(EXPECTATION_FAILED)
                    .link("ModuleServiceImpl/save")
                    .buildRuntime(ImpossibleToSaveException.class);
        }
    }
}
