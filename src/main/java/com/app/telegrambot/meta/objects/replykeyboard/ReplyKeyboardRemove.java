package com.app.telegrambot.meta.objects.replykeyboard;

import com.app.telegrambot.meta.interfaces.BotApiObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyKeyboardRemove implements Keyboard, BotApiObject {

    private static final String REMOVE_KEYBOARD_FIELD = "remove_keyboard";
    private static final String SELECTIVE_FIELD = "selective";

    @JsonProperty(REMOVE_KEYBOARD_FIELD)
    private boolean removeKeyboard;

    @JsonProperty(SELECTIVE_FIELD)
    private boolean selective;
}
