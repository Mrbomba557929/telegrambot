package com.app.telegrambot.meta.methods.send.objects;

import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@JsonDeserialize
@Data
@AllArgsConstructor
@Builder
public class AnswerCallbackQuery implements BotApiObject {

    public static final String CALLBACK_QUERY_ID_FIELD = "callback_query_id";
    public static final String TEXT_FIELD = "text";
    public static final String SHOW_ALERT = "show_alert";

    @JsonProperty(CALLBACK_QUERY_ID_FIELD)
    private String callbackQueryId;

    @JsonProperty(TEXT_FIELD)
    private String text;

    @JsonProperty(SHOW_ALERT)
    private boolean showAlert;
}
