package com.app.telegrambot.methods.send;

import com.app.telegrambot.domain.base.Message;
import com.app.telegrambot.domain.base.request.SendMessage;
import com.app.telegrambot.exception.compiletime.impl.TelegramApiException;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for send messages, files, photos.
 */
public interface Senderable {

    /**
     * Methods designed to send message.
     *
     * @param sendMessage - message to be sent.
     * @return the response {@link Message}.
     */
    CompletableFuture<Message> sendMessage(SendMessage sendMessage) throws TelegramApiException;
}
