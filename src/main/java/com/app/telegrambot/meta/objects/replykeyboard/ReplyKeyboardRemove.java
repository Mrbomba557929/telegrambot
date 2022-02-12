package com.app.telegrambot.meta.objects.replykeyboard;

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
public class ReplyKeyboardRemove implements Keyboard {

    private static final String REMOVE_KEYBOARD_FIELD = "remove_keyboard";
    private static final String SELECTIVE_FIELD = "selective";

    @JsonProperty(REMOVE_KEYBOARD_FIELD)
    private boolean removeKeyboard;

    @JsonProperty(SELECTIVE_FIELD)
    private boolean selective;
}
