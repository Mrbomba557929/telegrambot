package com.app.telegrambot.meta.methods.send;

import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.meta.objects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Slf4j
@Component
public class MessageSender implements Senderable {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${telegrambot.url}")
    private String url;

    public MessageSender() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @Async("async")
    @Override
    public CompletableFuture<Message> sendMessage(SendMessage sendMessage)
            throws TelegramApiException {

        log.info("sendMessage starts {}",  sendMessage);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity;

        try {
            log.info("{}", objectMapper.writeValueAsString(sendMessage));
            entity = new HttpEntity<>(objectMapper.writeValueAsString(sendMessage), headers);
        } catch (JsonProcessingException e) {
            throw ExceptionFactory.exceptionBuilder(e.getMessage())
                    .status(NOT_ACCEPTABLE)
                    .link("Command/sendMessage")
                    .buildCompileTime(TelegramApiException.class);
        }

        ResponseEntity<Message> response = restTemplate.postForEntity(url + "/sendMessage", entity, Message.class);

        log.info("response: {}", response);

        if ((response.getStatusCodeValue() >= 400 && response.getStatusCodeValue() <= 499) || !response.hasBody()) {

            log.error("An error occurred while sending the message.");

            throw ExceptionFactory.exceptionBuilder("An error occurred while sending the message.")
                    .status(response.getStatusCode())
                    .link("Command/sendMessage")
                    .buildCompileTime(TelegramApiException.class);
        }

        return CompletableFuture.completedFuture(response.getBody());
    }
}
