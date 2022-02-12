package com.app.telegrambot.command;

import com.app.telegrambot.command.impl.UnknownCommand;
import com.app.telegrambot.command.impl.menu.ShowMenuCommand;
import com.app.telegrambot.command.impl.module.CreateModuleCommand;
import com.app.telegrambot.command.impl.state.StopStateCommand;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.app.telegrambot.command.CommandName.*;

@Component
public class CommandContainer {

    private final ImmutableMap<CommandName, Command> commands;

    @Autowired
    public CommandContainer(CreateModuleCommand createModuleCommand, StopStateCommand stopStateCommand, ShowMenuCommand showMenuCommand) {
        commands = ImmutableMap.<CommandName, Command>builder()
                .put(CREATE_MODULE, createModuleCommand)
                .put(STOP_STATE, stopStateCommand)
                .put(SHOW_MENU, showMenuCommand)
                .build();
    }

    /**
     * Method allows you to get a command from the repository.
     *
     * @param command - the name of the {@link Command}.
     * @return found the {@link Command} or the default {@link UnknownCommand}.
     */
    public Command retrieve(CommandName command) {
        return commands.getOrDefault(command, new UnknownCommand());
    }
}
