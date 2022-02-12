package com.app.telegrambot.fms;

import com.app.telegrambot.meta.methods.get.Update;

@FunctionalInterface
public interface Transition {
    void execute(Update t);
}
