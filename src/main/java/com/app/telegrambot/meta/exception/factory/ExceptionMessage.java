package com.app.telegrambot.meta.exception.factory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE("Ошибка: передан недопустимый аргумент!");

    private final String message;
}
