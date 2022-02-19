package com.app.telegrambot.command.impl.word;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.domain.еnum.ParseMode;
import com.app.telegrambot.fms.PartOfState;
import com.app.telegrambot.fms.State;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
import com.app.telegrambot.meta.methods.send.objects.SendMessage;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.service.ModuleService;
import com.app.telegrambot.service.WordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShowAllWordsInModuleCommand implements Command {

    public static final String SHOW_ALL_WORDS_IN_MODULE_COMMAND_EXCEPTION =
            """
            Напишите название модуля, у которого вы хотите посмотреть слова.
            """;

    private final WordService wordService;
    private final ModuleService moduleService;
    private final MessageSender messageSender;
    private final StateMachine stateMachine;

    @Override
    public void execute(Update update) {
        try {

            messageSender.send(SendMessage.builder()
                    .text(SHOW_ALL_WORDS_IN_MODULE_COMMAND_EXCEPTION)
                    .parseMode(ParseMode.MARKDOWN)
                    .chatId(update.message().chat().id())
                    .build());

            stateMachine.addState(update.message().from().idLong(), State.builder()
                    .transition(this::showAllWordsInModule)
                    .build());

        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при отправке запроса: {}", e.getMessage());
        }
    }

    @PartOfState
    private void showAllWordsInModule(Update update) {
        ModuleEntity module = moduleService.findByNameAndUserId(update.message().text(), update.message().from().idLong());
    }
}
