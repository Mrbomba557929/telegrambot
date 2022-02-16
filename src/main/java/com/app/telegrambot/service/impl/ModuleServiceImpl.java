package com.app.telegrambot.service.impl;

import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.meta.exception.runtime.impl.ImpossibleToSaveException;
import com.app.telegrambot.meta.exception.runtime.impl.NotFoundException;
import com.app.telegrambot.repository.ModuleRepository;
import com.app.telegrambot.service.ModuleService;
import com.app.telegrambot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Implementation of the {@link ModuleService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final UserService userService;

    @Override
    public ModuleEntity save(String name, Long userId) {
        try {
            ModuleEntity moduleEntity = ModuleEntity.builder()
                    .name(name)
                    .createdAt(Instant.now())
                    .user(userService.findById(userId))
                    .build();
            return moduleRepository.save(moduleEntity);
        } catch (DataAccessException e) {
            log.error("Impossible to save the user. {}", e.getMessage());
            throw ExceptionFactory.exceptionBuilder(e.getMessage())
                    .link("ModuleServiceImpl/save")
                    .buildRuntime(ImpossibleToSaveException.class);
        }
    }

    @Override
    public Page<ModuleEntity> findAll(int page, int size) {
        return moduleRepository.findAll(PageRequest.of(page - 1, size, Sort.by("createdAt").descending()));
    }

    @Override
    public ModuleEntity findById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.exceptionBuilder("Пользователь не был найден!")
                        .link("ModuleServiceImpl/findById")
                        .buildRuntime(NotFoundException.class));
    }

    @Override
    public ModuleEntity findByNameAndUserId(String name, Long id) {
        return moduleRepository.findByNameAndUserId(name, id)
                .orElseThrow(() -> ExceptionFactory.exceptionBuilder("Пользователь не был найден!")
                        .link("ModuleServiceImpl/findById")
                        .buildRuntime(NotFoundException.class));
    }
}
