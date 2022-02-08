package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.base.Update;
import com.app.telegrambot.domain.entity.ModuleEntity;
import org.springframework.stereotype.Component;

/**
 * {@link Command} for creating the {@link ModuleEntity} entity.
 */
@Component
public class CreateModuleCommand implements Command {

    @Override
    public void execute(Update update) {

    }
}
