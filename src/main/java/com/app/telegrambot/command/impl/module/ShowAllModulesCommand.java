package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.domain.еnum.ParseMode;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.exception.factory.ExceptionFactory;
import com.app.telegrambot.meta.exception.runtime.impl.IllegalArgumentException;
import com.app.telegrambot.meta.methods.send.impl.EditMessageTextSender;
import com.app.telegrambot.meta.methods.send.objects.EditMessageText;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
import com.app.telegrambot.meta.methods.send.objects.SendMessage;
import com.app.telegrambot.meta.objects.replykeyboard.InlineKeyboardMarkup;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.InlineKeyboardButton;
import com.app.telegrambot.meta.objects.replykeyboard.paginator.InlineKeyboardPaginator;
import com.app.telegrambot.service.ModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.app.telegrambot.meta.exception.factory.ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE;
import static java.lang.String.format;

/**
 * {@link Command} shows all modules.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ShowAllModulesCommand implements Command {

    public static final Integer NUMBER_OF_PAGES_IN_BLOCK = 5;
    public static final Integer INITIAL_PAGE_SIZE = 1;
    public static final String FIND_ALL_MODULES_COMMAND = "/modules";
    public static final String FIND_ALL_MODULES_WITH_CURRENT_PAGE_REGEX = "/modules:\\d+";
    public static final String FIND_MODULE_COMMAND = "/module";
    public static final String DOT = "·";
    public static final String DELIMITER = ":";

    private final MessageSender messageSender;
    private final EditMessageTextSender editMessageTextSender;
    private final ModuleService moduleService;
    private final InlineKeyboardPaginator inlineKeyboardPaginator;

    @Override
    public void execute(Update update) {
        try {

            log.info("Пришло сообщение в find modules: {}", update.message().text());

            int page = update.message().text().matches(FIND_ALL_MODULES_WITH_CURRENT_PAGE_REGEX) ?
                    Integer.parseInt(update.message().text().split(DELIMITER)[1]) :
                    INITIAL_PAGE_SIZE;

            log.info("Страница: {}", page);

            Page<ModuleEntity> modules = moduleService.findAll(page, NUMBER_OF_PAGES_IN_BLOCK);

            log.info("Количество страниц: {}, Количество элементов: {}", modules.getTotalPages(), modules.getTotalElements());
            log.info("Количество элементов: {}", modules.getContent().size());

            if (update.hasCallBackQuery()) {
                editMessageTextSender.send(EditMessageText.builder()
                        .chatId(update.message().chat().id())
                        .messageId(Math.toIntExact(update.message().id()))
                        .text(modules.getContent().get(0).toString())
                        .parseMode(ParseMode.MARKDOWN)
                        .replyMarkup(generateKeyboard(modules, page))
                        .build());
            } else {
                messageSender.send(SendMessage.builder()
                        .chatId(update.message().chat().id())
                        .text(modules.getContent().get(0).toString())
                        .parseMode(ParseMode.MARKDOWN)
                        .replyMarkup(generateKeyboard(modules, page))
                        .build());
            }

        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при отправке запроса: {}", e.getMessage());
        }
    }

    private InlineKeyboardMarkup generateKeyboard(Page<ModuleEntity> modules, int page) {
        return InlineKeyboardMarkup.builder()
                .zip(generateButtonsForModules(modules.getContent()))
                .zip(inlineKeyboardPaginator.paginate(modules.getTotalPages(), page, FIND_ALL_MODULES_COMMAND + ":%d").getInlineKeyboard())
                .zip(generateBottomButtons())
                .withRow(modules.getContent().size() > 0
                        ? List.of(
                        InlineKeyboardButton.builder()
                                .text("Посмотреть выбранный модуль")
                                .callbackData(format("/module:%d:showModule", modules.getContent().get(0).getId()))
                                .build())
                        : null)
                .build();
    }

    private List<List<InlineKeyboardButton>> generateButtonsForModules(List<ModuleEntity> modules) {

        if (Objects.isNull(modules)) {
            throw ExceptionFactory.exceptionBuilder(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE)
                    .link("ShowAllModulesCommand/generateButtonsForModules")
                    .buildRuntime(IllegalArgumentException.class);
        }

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        for (int i = 0; i < modules.size(); i++) {
            buttons.add(List.of(InlineKeyboardButton.builder()
                            .text(i == 0 ? (DOT + modules.get(i).getName() + DOT) : (modules.get(i).getName()))
                            .callbackData(format("%s:%s:showAllModules", FIND_MODULE_COMMAND, modules.get(i).getId()))
                            .build()));
        }

        return buttons;
    }

    private List<List<InlineKeyboardButton>> generateBottomButtons() {
        return List.of(
                List.of(
                        InlineKeyboardButton.builder()
                                .text("Меню")
                                .callbackData("/menu:previous")
                                .build()
                )
        );
    }
}
