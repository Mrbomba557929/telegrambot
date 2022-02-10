package com.app.telegrambot.fms;

import com.app.telegrambot.exception.runtime.impl.NotFoundException;
import com.app.telegrambot.exception.factory.ExceptionFactory;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

@Data
@Component
public class StateMachine {

    private final Map<Integer, State> stateMap = new HashMap<>();

    public void transition(Integer chatId, State state) {
        stateMap.put(chatId, state);
    }

    public State retrieve(Integer chatId) {

        if (stateMap.containsKey(chatId)) {
            return stateMap.get(chatId);
        }

        throw ExceptionFactory.exceptionBuilder("Error: State not found!")
                .status(EXPECTATION_FAILED)
                .link("StateMachine/retrieve")
                .buildRuntime(NotFoundException.class);
    }

    public void remove(Integer chatId) {
        stateMap.remove(chatId);
    }

    public boolean contains(Integer chatId) {
        return stateMap.containsKey(chatId);
    }
}
