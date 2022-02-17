package com.app.telegrambot.meta.methods.send.impl;

import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.meta.methods.send.Sender;
import com.app.telegrambot.meta.methods.send.objects.EditMessageText;
import com.app.telegrambot.meta.objects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class EditMessageTextSender extends Sender<Message, EditMessageText> {

    public static final String EDIT_MESSAGE_TEXT_METHOD = "/editMessageText";

    public EditMessageTextSender() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public CompletableFuture<Message> send(EditMessageText sendMessage) throws TelegramApiException {

        log.info("Отправка 'EditMessageText' началась. Параметр {}",  sendMessage);

        HttpEntity<String> entity;

        try {
            String jsonValue = objectMapper.writeValueAsString(sendMessage);
            log.info("JSON значение: {}", jsonValue);
            entity = new HttpEntity<>(jsonValue, headers);
        } catch (JsonProcessingException e) {
            throw ExceptionFactory.exceptionBuilder(e.getMessage())
                    .link("EditMessageTextSender/send")
                    .buildCompileTime(TelegramApiException.class);
        }

        ResponseEntity<Message> response = restTemplate.postForEntity(url + EDIT_MESSAGE_TEXT_METHOD, entity, Message.class);

        log.info("Ответ получен: {}", response);

        if ((response.getStatusCodeValue() >= 400 && response.getStatusCodeValue() <= 499) || !response.hasBody()) {

            log.error("Произошла ошибка при вызове метода 'answerCallbackQuery'.");

            throw ExceptionFactory.exceptionBuilder("Произошла ошибка при вызове метода 'answerCallbackQuery'.")
                    .link("Command/send")
                    .buildCompileTime(TelegramApiException.class);
        }

        return CompletableFuture.completedFuture(response.getBody());
    }
}
