package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.meta.methods.send.SendMessage;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.fms.State;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
import com.app.telegrambot.service.ModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * {@link Command} for creating the {@link ModuleEntity} entity.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateModuleCommand implements Command {

    public static final String CREATE_MODULE_COMMAND_MESSAGE = "%s, введите название модуля.";
    public static final String ASK_FOR_MODULE_NAME_MESSAGE = "%s, модуль '%s' успешно создан.";

    private final StateMachine stateMachine;
    private final MessageSender messageSender;
    private final ModuleService moduleService;

    @Override
    public void execute(Update update) {
        try {
            messageSender.send(SendMessage.builder()
                    .text(format(CREATE_MODULE_COMMAND_MESSAGE, update.message().from().fio()))
                    .chatId(update.message().chat().id())
                    .build());

            stateMachine.start(update.message().from().idLong(), State.builder()
                    .transition(this::askForModuleName)
                    .objectBuilder(ModuleEntity.builder())
                    .build());

        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }

    /**
     * The method asks the user for the module name
     *
     * @param update with all information.
     */
    public void askForModuleName(Update update) {
        try {
            log.info("Начало работы askForModuleName метода. Имя модуля: {}", update.message().text());

            ModuleEntity savedModule = moduleService.save(update.message().text(), update.message().from().idLong());

            messageSender.send(SendMessage.builder()
                    .text(format(ASK_FOR_MODULE_NAME_MESSAGE, update.message().from().fio(), savedModule.getName()))
                    .chatId(update.message().chat().id())
                    .build());

            stateMachine.stop(update.message().from().idLong());
        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }
}
