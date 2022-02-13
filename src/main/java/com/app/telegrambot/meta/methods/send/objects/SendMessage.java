package com.app.telegrambot.meta.methods.send.objects;

import com.app.telegrambot.domain.еnum.ParseMode;
import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.app.telegrambot.meta.objects.replykeyboard.Keyboard;
import com.app.telegrambot.meta.objects.replykeyboard.ReplyKeyboardRemove;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@JsonDeserialize
@Data
@AllArgsConstructor
@Builder
public class SendMessage implements BotApiObject {

    private static final String CHAT_ID_FIELD = "chat_id";
    private static final String TEXT_FIELD = "text";
    private static final String REPLY_MARKUP_FIELD = "reply_markup";
    private static final String PARSE_MODE_FIELD = "parse_mode";

    @JsonProperty(CHAT_ID_FIELD)
    private Integer chatId;

    @JsonProperty(TEXT_FIELD)
    private String text;

    @JsonProperty(REPLY_MARKUP_FIELD)
    private Keyboard replyMarkup;

    @JsonProperty(PARSE_MODE_FIELD)
    private ParseMode parseMode;

    public SendMessage() {
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        replyKeyboardRemove.setRemoveKeyboard(true);
        replyMarkup = replyKeyboardRemove;
    }
}
