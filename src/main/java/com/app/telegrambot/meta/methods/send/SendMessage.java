package com.app.telegrambot.meta.methods.send;

import com.app.telegrambot.meta.interfaces.TelegramBot;
import com.app.telegrambot.meta.objects.replykeyboard.Keyboard;
import com.app.telegrambot.meta.objects.replykeyboard.ReplyKeyboardRemove;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@JsonDeserialize
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class SendMessage implements TelegramBot {

    private static final String CHAT_ID_FIELD = "chat_id";
    private static final String TEXT_FIELD = "text";
    private static final String REPLY_MARKUP_FIELD = "reply_markup";

    @JsonProperty(CHAT_ID_FIELD)
    private Integer chatId;

    @JsonProperty(TEXT_FIELD)
    private String text;

    @JsonProperty(REPLY_MARKUP_FIELD)
    private Keyboard replyMarkup;

    public SendMessage() {
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        replyKeyboardRemove.setRemoveKeyboard(true);
        replyMarkup = replyKeyboardRemove;
    }
}
