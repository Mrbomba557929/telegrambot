package com.app.telegrambot.meta.methods.send.impl;

import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.meta.methods.send.objects.SendMessage;
import com.app.telegrambot.meta.methods.send.Senderable;
import com.app.telegrambot.meta.objects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class MessageSender extends Senderable<Message, SendMessage> {

    public static final String SEND_MESSAGE_METHOD = "/sendMessage";

    public MessageSender() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public CompletableFuture<Message> send(SendMessage sendMessage) throws TelegramApiException {

        log.info("Отправка 'SendMessage' началась. Параметр: {}",  sendMessage);

        HttpEntity<String> entity;

        try {
            String jsonValue = objectMapper.writeValueAsString(sendMessage);
            log.info("JSON значение: {}", jsonValue);
            entity = new HttpEntity<>(jsonValue, headers);
        } catch (JsonProcessingException e) {
            throw ExceptionFactory.exceptionBuilder(e.getMessage())
                    .link("Command/send")
                    .buildCompileTime(TelegramApiException.class);
        }

        ResponseEntity<Message> response = restTemplate.postForEntity(url + SEND_MESSAGE_METHOD, entity, Message.class);

        log.info("Ответ получен: {}", response);

        if ((response.getStatusCodeValue() >= 400 && response.getStatusCodeValue() <= 499) || !response.hasBody()) {

            log.error("Произошла ошибка при вызове метода 'sendMessage'.");

            throw ExceptionFactory.exceptionBuilder("Произошла ошибка при вызове метода 'sendMessage'.")
                    .link("Command/send")
                    .buildCompileTime(TelegramApiException.class);
        }

        return CompletableFuture.completedFuture(response.getBody());
    }
}
