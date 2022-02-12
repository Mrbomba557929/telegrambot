package com.app.telegrambot.fms;

import com.app.telegrambot.domain.bot.response.Update;

@FunctionalInterface
public interface Transition {
    void execute(Update t);
}
