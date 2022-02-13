package com.app.telegrambot.fms;

import com.app.telegrambot.meta.objects.Update;

@FunctionalInterface
public interface Transition {
    void execute(Update t);
}
