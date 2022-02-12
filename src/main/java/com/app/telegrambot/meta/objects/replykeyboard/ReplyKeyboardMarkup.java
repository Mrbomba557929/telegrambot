package com.app.telegrambot.meta.objects.replykeyboard;

import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiValidationException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.meta.interfaces.Validable;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.KeyboardRow;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.List;
import java.util.Objects;

import static com.app.telegrambot.meta.exception.factory.ExceptionMessage.WITHOUT_MESSAGE;

@JsonDeserialize
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyKeyboardMarkup implements ReplyKeyboard, Validable {

    private static final String KEYBOARD = "keyboard";
    private static final String RESIZE_KEYBOARD = "resize_keyboard";
    private static final String SELECTIVE = "selective";

    @JsonProperty(KEYBOARD)
    private List<KeyboardRow> keyboard;

    @JsonProperty(RESIZE_KEYBOARD)
    private boolean resizeKeyboard;

    @JsonProperty(SELECTIVE)
    private boolean selective;

    @Override
    public void validate() throws TelegramApiValidationException {
        if (Objects.isNull(keyboard)) {
            throw ExceptionFactory.exceptionBuilder(WITHOUT_MESSAGE)
                    .link("ReplyKeyboardMarkup/validate")
                    .buildCompileTime(TelegramApiValidationException.class);
        }

        for (KeyboardRow row : keyboard) {
            row.validate();
        }
    }
}
