package com.app.telegrambot.domain.bot.request;

import com.app.telegrambot.domain.bot.keyboard.ReplyKeyboard;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SendMessage {

    @JsonProperty("chat_id")
    private Integer chatId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("reply_markup")
    private ReplyKeyboard replyMarkup;
}
