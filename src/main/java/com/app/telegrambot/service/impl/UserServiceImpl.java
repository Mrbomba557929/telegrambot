package com.app.telegrambot.service.impl;

import com.app.telegrambot.meta.objects.User;
import com.app.telegrambot.domain.entity.UserEntity;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.meta.exception.runtime.impl.ImpossibleToSaveException;
import com.app.telegrambot.meta.exception.runtime.impl.NotFoundException;
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
            UserEntity userEntity = UserEntity.builder()
                    .id(user.idLong())
                    .firstName(user.firstName())
                    .lastName(user.lastName())
                    .username(user.username())
                    .isBot(user.isBot())
                    .languageCode(user.languageCode())
                    .build();
            return userRepository.save(userEntity);
        } catch (DataAccessException e) {
            log.error("Impossible to save the user. {}", e.getMessage());
            throw ExceptionFactory.exceptionBuilder(e.getMessage())
                    .status(EXPECTATION_FAILED)
                    .link("UserServiceImpl/save")
                    .buildRuntime(ImpossibleToSaveException.class);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> ExceptionFactory.exceptionBuilder("Error: user not found!")
                        .status(EXPECTATION_FAILED)
                        .link("UserServiceImpl/findById")
                        .buildRuntime(NotFoundException.class));
    }
}
