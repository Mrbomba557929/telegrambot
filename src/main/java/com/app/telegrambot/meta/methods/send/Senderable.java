package com.app.telegrambot.meta.methods.send;

import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.objects.Message;

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
