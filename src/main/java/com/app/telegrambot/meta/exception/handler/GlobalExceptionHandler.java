package com.app.telegrambot.meta.exception.handler;

import com.app.telegrambot.context.TelegramBotContextHolder;
import com.app.telegrambot.meta.methods.send.objects.SendMessage;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.exception.runtime.ApplicationRuntimeException;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
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
            sendMessage.setText("Произошла какая то ошибка. Попробуйте еще раз выполнить предыдущее действие.");
            sendMessage.setChatId(TelegramBotContextHolder.UPDATE.message().chat().id());
            sender.send(sendMessage);
        } catch (TelegramApiException ex) {
            log.error("An error occurred while sending the message.");
        }
    }
}
