package com.app.telegrambot.meta.methods.send.objects;

import com.app.telegrambot.domain.еnum.ParseMode;
import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.app.telegrambot.meta.objects.replykeyboard.InlineKeyboardMarkup;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@JsonDeserialize
@Data
@AllArgsConstructor
@Builder
public class EditMessageText implements BotApiObject {

    public static final String CHAT_ID_FIELD = "chat_id";
    public static final String MESSAGE_ID_FIELD = "message_id";
    public static final String TEXT_FIELD = "text";
    public static final String PARSE_MODE_FIELD = "parse_mode";
    public static final String REPLY_MARKUP_FIELD = "reply_markup";

    @JsonProperty(CHAT_ID_FIELD)
    private Integer chatId;

    @JsonProperty(MESSAGE_ID_FIELD)
    private Integer messageId;

    @JsonProperty(TEXT_FIELD)
    private String text;

    @JsonProperty(PARSE_MODE_FIELD)
    private ParseMode parseMode;

    @JsonProperty(REPLY_MARKUP_FIELD)
    private InlineKeyboardMarkup replyMarkup;
}
