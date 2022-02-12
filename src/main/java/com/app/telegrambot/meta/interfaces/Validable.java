package com.app.telegrambot.meta.interfaces;

import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiValidationException;

/**
 * @brief Interface that can be implemented by objects that knows how to validate their fields
 */
public interface Validable {

    /**
     * Validates that mandatory fields are filled and optional objects
     * @throws TelegramApiValidationException If any mandatory field is empty
     */
    void validate() throws TelegramApiValidationException;
}
