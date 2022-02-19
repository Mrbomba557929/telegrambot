package com.app.telegrambot.command.impl.word;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.domain.entity.TranslationEntity;
import com.app.telegrambot.domain.entity.WordEntity;
import com.app.telegrambot.domain.еnum.ParseMode;
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

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddWordsInModuleCommand implements Command {

    public static final String ADD_WORDS_IN_MODULE_COMMAND_MESSAGE =
            """
            Началась запись слов в модуль.
            Чтобы добавить слово и перевод воспользуйтесь одним из паттернов (Без одинарных ковычек!!):
            'слово' - 'перевод', 'перевод' ..., 'слово':'перевод', 'перевод', ..., 'слово' 'перевод', 'перевод', ...
            Чтобы отменить запись слов в модуль напишите '/stop' или нажмите на кнопку.
            """;
    public static final String WORD_COULD_NOT_BE_PARSED_MESSAGE =
            """
            Ваш текст не был распознан. Проверьте правильность написания сообщения!
            """;
    public static final String WORD_SAVED_SUCCESSFULLY =
            """
            Слово с переводом успешно добавлено!
            """;
    public static final String DELIMITER = ":";

    public static final String GENERAL_PATTERN_FOR_ADDING_WORDS = "^.+\\s*(-|:|\\s+)\\s*([a-zA-Z0-9А-Яа-яё]+,\\s*)+[a-zA-Z0-9А-Яа-яё]+\\s*$";
    public static final String GENERAL_PATTER_FOR_SEPARATING_WORD_AND_TRANSLATIONS = "\s*(:|-|\\s+)\s*";

    private final WordService wordService;
    private final ModuleService moduleService;
    private final MessageSender messageSender;
    private final StateMachine stateMachine;

    @Override
    public void execute(Update update) {
        try {

            messageSender.send(SendMessage.builder()
                    .text(ADD_WORDS_IN_MODULE_COMMAND_MESSAGE)
                    .parseMode(ParseMode.MARKDOWN)
                    .chatId(update.message().chat().id())
                    .build());

            stateMachine.addState(update.message().from().idLong(), State.builder()
                    .transition(this::addWord)
                    .object(update.message().text().split(DELIMITER)[1])
                    .build());

        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при отправке запроса: {}", e.getMessage());
        }
    }

    public void addWord(Update update) {
        try {

            if (!update.message().text().matches(GENERAL_PATTERN_FOR_ADDING_WORDS)) {
                messageSender.send(SendMessage.builder()
                        .text(WORD_COULD_NOT_BE_PARSED_MESSAGE)
                        .parseMode(ParseMode.MARKDOWN)
                        .chatId(update.message().chat().id())
                        .build());

                return;
            }

            String moduleName = (String) stateMachine.retrieve(update.message().from().idLong()).object();
            ModuleEntity module = moduleService.findByNameAndUserId(moduleName, update.message().from().idLong());

            wordService.save(WordEntity.builder()
                    .word(update.message().text().split(GENERAL_PATTER_FOR_SEPARATING_WORD_AND_TRANSLATIONS)[0])
                    .translations(convertTextToSetOfTranslations(update.message().text().split(GENERAL_PATTER_FOR_SEPARATING_WORD_AND_TRANSLATIONS)[1]))
                    .module(module)
                    .build());

            messageSender.send(SendMessage.builder()
                    .text(WORD_SAVED_SUCCESSFULLY)
                    .parseMode(ParseMode.MARKDOWN)
                    .chatId(update.message().chat().id())
                    .build());

        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при отправке запроса: {}", e.getMessage());
        }
    }

    private Set<TranslationEntity> convertTextToSetOfTranslations(String translations) {
        return Arrays.stream(translations.split(",\s*"))
                .map(translate -> TranslationEntity.builder()
                        .translation(translate)
                        .build())
                .collect(Collectors.toSet());
    }
}
