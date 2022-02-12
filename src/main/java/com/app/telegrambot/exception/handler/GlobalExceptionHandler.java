package com.app.telegrambot.exception.handler;

import com.app.telegrambot.context.TelegramBotContextHolder;
import com.app.telegrambot.domain.bot.request.SendMessage;
import com.app.telegrambot.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.exception.runtime.ApplicationRuntimeException;
import com.app.telegrambot.methods.send.MessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSender sender;

    @ExceptionHandler(value = {ApplicationRuntimeException.class})
    public void handleApplicationRuntimeException(ApplicationRuntimeException e) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("An error has occurred. Try again.");
            sendMessage.setChatId(TelegramBotContextHolder.UPDATE.message().chat().id());
            sender.sendMessage(sendMessage);
        } catch (TelegramApiException ex) {
            log.error("An error occurred while sending the message.");
        }
    }
}
