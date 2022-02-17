package com.app.telegrambot.fms;

import com.app.telegrambot.meta.exception.runtime.impl.NotFoundException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class StateMachine {

    private final Map<Long, State> stateMap = new HashMap<>();

    public State retrieve(Long fromId) {

        if (stateMap.containsKey(fromId)) {
            return stateMap.get(fromId);
        }

        throw ExceptionFactory.exceptionBuilder("Error: State not found!")
                .link("StateMachine/build")
                .buildRuntime(NotFoundException.class);
    }

    public void addState(Long fromId, State state) {
        stateMap.put(fromId, state);
    }

    public State transition(Long fromId, Transition transition) {

        if (!stateMap.containsKey(fromId)) {
            throw ExceptionFactory.exceptionBuilder("Error: State not found!")
                    .link("StateMachine/build")
                    .buildRuntime(NotFoundException.class);
        }

        stateMap.put(fromId, State.builder()
                .transition(transition)
                .object(stateMap.get(fromId).object())
                .build());

        return retrieve(fromId);
    }

    public void stop(Long fromId) {
        stateMap.remove(fromId);
    }

    public boolean contains(Long fromId) {
        return stateMap.containsKey(fromId);
    }
}
