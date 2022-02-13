package com.app.telegrambot.meta.methods.send.objects;

import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record AnswerCallbackQuery(@JsonProperty(CALLBACK_QUERY_ID_FIELD) String callbackQueryId,
                                   @JsonProperty(TEXT_FIELD) String text,  @JsonProperty(SHOW_ALERT) boolean showAlert)
    implements BotApiObject {

    public static final String CALLBACK_QUERY_ID_FIELD = "callback_query_id";
    public static final String TEXT_FIELD = "text";
    public static final String SHOW_ALERT = "show_alert";

    @Builder
    public AnswerCallbackQuery {
    }
}
