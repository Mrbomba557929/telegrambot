package com.app.telegrambot.meta.methods.send;

import com.app.telegrambot.meta.objects.replykeyboard.ReplyKeyboard;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@JsonDeserialize
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMessage {

    private static final String CHAT_ID = "chat_id";
    private static final String TEXT = "text";
    private static final String REPLY_MARKUP = "reply_markup";

    @JsonProperty(CHAT_ID)
    private Integer chatId;

    @JsonProperty(TEXT)
    private String text;

    @JsonProperty(REPLY_MARKUP)
    private ReplyKeyboard replyMarkup;
}
