package com.app.telegrambot.command;

import com.app.telegrambot.domain.base.request.SendMessage;

/**
 * Interface for send messages, files, photos.
 */
public interface Sender {

    /**
     * Methods designed to send message.
     *
     * @param sendMessage - message to be sent.
     */
    void sendMessage(SendMessage sendMessage);
}
