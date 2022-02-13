package com.app.telegrambot.fms;

import lombok.Builder;

public record State (Transition transition, Object objectBuilder) {

    @Builder
    public State {
    }
}
