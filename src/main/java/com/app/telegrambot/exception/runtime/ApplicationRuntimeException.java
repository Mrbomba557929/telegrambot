package com.app.telegrambot.exception.runtime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApplicationRuntimeException extends RuntimeException {
    protected HttpStatus status;
    protected String link;
    protected String message;
}
