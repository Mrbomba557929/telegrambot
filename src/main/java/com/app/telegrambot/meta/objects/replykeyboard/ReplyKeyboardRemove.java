package com.app.telegrambot.meta.objects.replykeyboard;

import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiValidationException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.meta.exception.factory.ExceptionMessage;
import com.app.telegrambot.meta.interfaces.Validable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.Objects;

import static com.app.telegrambot.meta.exception.factory.ExceptionMessage.WITHOUT_MESSAGE;

@JsonDeserialize
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyKeyboardRemove implements ReplyKeyboard, Validable {

    private static final String REMOVE_KEYBOARD_FIELD = "remove_keyboard";
    private static final String SELECTIVE_FIELD = "selective";

    @JsonProperty(REMOVE_KEYBOARD_FIELD)
    private Boolean removeKeyboard;

    @JsonProperty(SELECTIVE_FIELD)
    private Boolean selective;

    @Override
    public void validate() throws TelegramApiValidationException {
        if (Objects.isNull(removeKeyboard)) {
            throw ExceptionFactory.exceptionBuilder(WITHOUT_MESSAGE)
                    .link("ReplyKeyboardRemove/validate")
                    .buildCompileTime(TelegramApiValidationException.class);
        }
    }
}
