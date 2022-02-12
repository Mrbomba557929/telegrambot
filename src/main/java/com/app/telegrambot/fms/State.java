package com.app.telegrambot.fms;

public record State (Transition transition, Object builder) {

    public State(Transition transition, Object builder) {
        this.transition = transition;
        this.builder = builder;
    }

    public static State create(Transition transition, Object builder) {
        return new State(transition, builder);
    }
}
