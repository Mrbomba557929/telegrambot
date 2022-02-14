package com.app.telegrambot.meta.methods.send;

import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * Abstract class for send messages, files, photos.
 */
public abstract class Sender<R, P> {

    protected final RestTemplate restTemplate;
    protected final ObjectMapper objectMapper;
    protected final HttpHeaders headers;

    @Value("${telegrambot.url}")
    protected String url;

    public Sender() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
        this.headers = new HttpHeaders();
    }

    /**
     * Methods designed to send message.
     *
     * @param sendMessage - message to be sent.
     * @return the response {@link R}.
     */
    @Async("async")
    public abstract CompletableFuture<R> send(P sendMessage) throws TelegramApiException;
}
