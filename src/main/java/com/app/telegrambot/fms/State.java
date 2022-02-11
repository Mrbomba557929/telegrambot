package com.app.telegrambot.fms;

public record State (boolean isActive, Transition transition, Object builder) {

    public State(Transition transition, Object object) {
        this(true, transition, object);
    }

    public static State create(Transition transition, Object builder) {
        return new State(transition, builder);
    }
}
