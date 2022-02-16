package com.app.telegrambot.meta.objects.replykeyboard.buttons;

import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InlineKeyboardButton implements BotApiObject {

    private static final String TEXT_FIELD = "text";
    private static final String URL_FIELD = "url";
    private static final String CALLBACK_DATA_FIELD = "callback_data";

    @JsonProperty(TEXT_FIELD)
    private String text;

    @JsonProperty(URL_FIELD)
    private String url;

    @JsonProperty(CALLBACK_DATA_FIELD)
    private String callbackData;
}
