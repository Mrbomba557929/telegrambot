package com.app.telegrambot.fms;

public record State (boolean isActive, Transition transition, Object builder) {

    public State(Transition transition, Object object) {
        this(true, transition, object);
    }

    public State(boolean isActive, Transition transition, Object builder) {
        this.isActive = isActive;
        this.transition = transition;
        this.builder = builder;
    }
}
