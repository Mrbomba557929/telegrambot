package com.app.telegrambot.fms;

public record State (Transition transition, Object builder) {
    public static State create(Transition transition, Object builder) {
        return new State(transition, builder);
    }
}
