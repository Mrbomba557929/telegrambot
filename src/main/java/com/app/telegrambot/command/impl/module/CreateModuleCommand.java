package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.base.request.SendMessage;
import com.app.telegrambot.domain.base.response.Update;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.fms.StateMachine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * {@link Command} for creating the {@link ModuleEntity} entity.
 */
@Component
@RequiredArgsConstructor
public class CreateModuleCommand extends Command {

    private final StateMachine stateMachine;

    @Override
    public void execute(Update update) {
        String message =
                """
                %s, введите название модуля.
                """.formatted(update.message().from().username());

        SendMessage sendMessage = SendMessage.builder()
                .text(message)
                .chatId(update.message().chat().id())
                .build();


    }

    public Integer test() {
        return 5;
    }
}
