package com.app.telegrambot.fms;

import com.app.telegrambot.meta.exception.runtime.impl.NotFoundException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

@Data
@Component
public class StateMachine {

    private final Map<Long, State> stateMap = new HashMap<>();

    public void start(Long fromId, State state) {
        stateMap.put(fromId, state);
    }

    public void transition(Long fromId, Transition transition) {

        if (!stateMap.containsKey(fromId)) {
            throw ExceptionFactory.exceptionBuilder("Error: State not found!")
                    .status(EXPECTATION_FAILED)
                    .link("StateMachine/retrieve")
                    .buildRuntime(NotFoundException.class);
        }

        stateMap.put(fromId, State.create(transition, stateMap.get(fromId).builder()));
    }

    public State retrieve(Long fromId) {

        if (stateMap.containsKey(fromId)) {
            return stateMap.get(fromId);
        }

        throw ExceptionFactory.exceptionBuilder("Error: State not found!")
                .status(EXPECTATION_FAILED)
                .link("StateMachine/retrieve")
                .buildRuntime(NotFoundException.class);
    }

    public void stop(Long fromId) {
        stateMap.remove(fromId);
    }

    public boolean contains(Long fromId) {
        return stateMap.containsKey(fromId);
    }
}
