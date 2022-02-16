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
public class KeyboardButton implements BotApiObject {

    private static final String TEXT_FIELD = "text";

    @JsonProperty(TEXT_FIELD)
    private String text;
}
