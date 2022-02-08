package com.app.telegrambot.domain.еnum;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CommandName {

    CREATE_MODULE("/cm");

    private final String commandName;
}
