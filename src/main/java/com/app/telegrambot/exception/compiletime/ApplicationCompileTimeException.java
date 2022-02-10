package com.app.telegrambot.exception.compiletime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApplicationCompileTimeException extends Exception {
    protected HttpStatus status;
    protected String link;
    protected String message;
}
