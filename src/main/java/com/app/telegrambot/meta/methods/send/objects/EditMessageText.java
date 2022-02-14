package com.app.telegrambot.meta.methods.send.objects;

import com.app.telegrambot.domain.еnum.ParseMode;
import com.app.telegrambot.meta.objects.replykeyboard.InlineKeyboardMarkup;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record EditMessageText(@JsonProperty(CHAT_ID_FIELD) Integer chatId, @JsonProperty(MESSAGE_ID_FIELD) Integer messageId,
                              @JsonProperty(TEXT_FIELD) String text, @JsonProperty(PARSE_MODE_FIELD) ParseMode parseMode,
                              @JsonProperty(REPLY_MARKUP_FIELD) InlineKeyboardMarkup replyMarkup) {

    public static final String CHAT_ID_FIELD = "chat_id";
    public static final String MESSAGE_ID_FIELD = "message_id";
    public static final String TEXT_FIELD = "text";
    public static final String PARSE_MODE_FIELD = "parse_mode";
    public static final String REPLY_MARKUP_FIELD = "reply_markup";

    @Builder
    public EditMessageText {
    }
}
