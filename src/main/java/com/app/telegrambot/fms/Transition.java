package com.app.telegrambot.fms;

import com.app.telegrambot.domain.base.response.Update;

import java.util.function.Consumer;

@FunctionalInterface
public interface Transition extends Consumer<Update> {
}
