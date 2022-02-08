package com.app.telegrambot.exception.factory;

import com.app.telegrambot.exception.ApplicationException;
import com.app.telegrambot.exception.business.UnknownExceptionClassException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
public class ExceptionFactory {

    public static Builder exceptionBuilder(ExceptionMessage message) {
        return new Builder(message);
    }

    public static Builder exceptionBuilder(String message) {
        return new Builder(message);
    }

    public static class Builder {

        private final String message;

        private HttpStatus status;
        private String link;

        public Builder(ExceptionMessage message) {
            this.message = message.getMessage();
        }

        public Builder(String message) {
            this.message = message;
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public ApplicationException build(Class<? extends ApplicationException> eClass) {
            ApplicationException res;

            try {
                res = eClass.getDeclaredConstructor().newInstance();
                res.setLink(link);
                res.setMessage(message);
                res.setStatus(status);
            } catch (
                    InstantiationException | IllegalAccessException |
                    NoSuchMethodException | InvocationTargetException e
            ) {
                throw new UnknownExceptionClassException();
            }

            return res;
        }
    }
}
