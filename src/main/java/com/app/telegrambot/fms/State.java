package com.app.telegrambot.fms;

import lombok.Builder;

public record State (boolean isActive, Transition transition) {

    @Builder
    public State {
    }
}
