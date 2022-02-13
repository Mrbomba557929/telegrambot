package com.app.telegrambot.meta.methods.send.impl;

import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.meta.methods.send.Senderable;
import com.app.telegrambot.meta.objects.AnswerCallbackQuery;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class AnswerCallbackQuerySender extends Senderable<Boolean, AnswerCallbackQuery> {

    public static final String ANSWER_CALLBACK_QUERY_METHOD = "/answerCallbackQuery";

    public AnswerCallbackQuerySender() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public CompletableFuture<Boolean> send(AnswerCallbackQuery answerCallbackQuery) throws TelegramApiException {

        log.info("Отправка 'AnswerCallbackQuery' началась. Параметр {}",  answerCallbackQuery);

        HttpEntity<String> entity;

        try {
            String jsonValue = objectMapper.writeValueAsString(answerCallbackQuery);
            log.info("JSON значение: {}", jsonValue);
            entity = new HttpEntity<>(jsonValue, headers);
        } catch (JsonProcessingException e) {
            throw ExceptionFactory.exceptionBuilder(e.getMessage())
                    .link("Command/send")
                    .buildCompileTime(TelegramApiException.class);
        }

        ResponseEntity<Boolean> response = restTemplate.postForEntity(url + ANSWER_CALLBACK_QUERY_METHOD, entity, Boolean.class);

        log.info("Ответ получен: {}", response);

        if ((response.getStatusCodeValue() >= 400 && response.getStatusCodeValue() <= 499) || !response.hasBody()) {

            log.error("Произошла ошибка при вызове метода 'answerCallbackQuery'.");

            throw ExceptionFactory.exceptionBuilder("An error occurred while sending the message.")
                    .link("Command/send")
                    .buildCompileTime(TelegramApiException.class);
        }

        return CompletableFuture.completedFuture(response.getBody());
    }
}
