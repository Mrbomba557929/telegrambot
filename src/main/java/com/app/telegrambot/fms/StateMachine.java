package com.app.telegrambot.fms;

import com.app.telegrambot.exception.business.NotFoundException;
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

    public void addState(Integer chatId, State state) {

        if (stateMap.containsKey(chatId)) {
            updateState(chatId, state);
            return;
        }

        stateMap.put(chatId, state);
    }

    public void updateState(Integer chatId, State newState) {

        if (!stateMap.containsKey(chatId)) {
            addState(chatId, newState);
            return;
        }

        stateMap.put(chatId, newState);
    }

    public State retrieveState(Integer chatId) {

        if (stateMap.containsKey(chatId)) {
            return stateMap.get(chatId);
        }

        throw ExceptionFactory.exceptionBuilder("Error: State not found!")
                .status(EXPECTATION_FAILED)
                .link("StateMachine/retrieveState")
                .build(NotFoundException.class);
    }
}
