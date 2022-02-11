package com.app.telegrambot.fms;

import com.app.telegrambot.domain.base.response.Update;

@FunctionalInterface
public interface Transition {
    void execute(Update t);
}
