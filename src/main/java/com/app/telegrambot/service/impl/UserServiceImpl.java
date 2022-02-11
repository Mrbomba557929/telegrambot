package com.app.telegrambot.service.impl;

import com.app.telegrambot.domain.base.User;
import com.app.telegrambot.domain.entity.UserEntity;
import com.app.telegrambot.exception.factory.ExceptionFactory;
import com.app.telegrambot.exception.runtime.impl.ImpossibleToSaveException;
import com.app.telegrambot.repository.UserRepository;
import com.app.telegrambot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity save(User user) {
        try {
            return userRepository.save(UserEntity.builder()
                    .id(user.id())
                    .firstName(user.firstName())
                    .lastName(user.lastName())
                    .username(user.username())
                    .languageCode(user.languageCode())
                    .isBot(user.isBot())
                    .build());
        } catch (DataAccessException e) {
            log.error("Impossible to save the user. {}", e.getMessage());
            throw ExceptionFactory.exceptionBuilder(e.getMessage())
                    .status(EXPECTATION_FAILED)
                    .link("UserServiceImpl/save")
                    .buildRuntime(ImpossibleToSaveException.class);
        }
    }

    @Override
    public boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }
}
