package com.app.telegrambot.fms;

import lombok.Builder;

public record State (Transition transition, Object object) {

    @Builder
    public State {
    }
}
