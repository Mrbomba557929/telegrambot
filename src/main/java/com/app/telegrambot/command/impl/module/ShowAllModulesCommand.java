package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.domain.еnum.ParseMode;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
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
            InlineKeyboardMarkup.Builder inlineKeyboardMarkup = InlineKeyboardMarkup.builder();

            log.info("Пришло сообщение в find modules: {}", update.message().text());

            int page = update.message().text().matches(FIND_ALL_MODULES_WITH_CURRENT_PAGE_REGEX) ?
                    Integer.parseInt(update.message().text().split(DELIMITER)[1]) :
                    INITIAL_PAGE_SIZE;

            log.info("Страница: {}", page);

            Page<ModuleEntity> modules = moduleService.findAll(page, NUMBER_OF_PAGES_IN_BLOCK);

            log.info("Количество страниц: {}, Количество элементов: {}", modules.getTotalPages(), modules.getTotalElements());
            log.info("Количество элементов: {}", modules.getContent().size());

            inlineKeyboardMarkup
                    .zip(generateButtonsFromModules(modules.getContent()))
                    .zip(inlineKeyboardPaginator
                            .paginate(modules.getTotalPages(), page, FIND_ALL_MODULES_COMMAND + ":%d")
                            .getInlineKeyboard());

            if (update.hasCallBackQuery()) {
                editMessageTextSender.send(EditMessageText.builder()
                        .chatId(update.message().chat().id())
                        .messageId(Math.toIntExact(update.message().id()))
                        .text(modules.getContent().get(0).toString())
                        .parseMode(ParseMode.MARKDOWN)
                        .replyMarkup(inlineKeyboardMarkup.build())
                        .build());
            } else {
                messageSender.send(SendMessage.builder()
                        .chatId(update.message().chat().id())
                        .text(modules.getContent().get(0).toString())
                        .parseMode(ParseMode.MARKDOWN)
                        .replyMarkup(inlineKeyboardMarkup.build())
                        .build());
            }
        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }

    private List<List<InlineKeyboardButton>> generateButtonsFromModules(List<ModuleEntity> modules) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        if (Objects.isNull(modules) || modules.size() == 0) {
            return buttons;
        }

        buttons.add(List.of(
                InlineKeyboardButton.builder()
                        .text(DOT + modules.get(0).getName() + DOT)
                        .callbackData(format("%s:%s", FIND_MODULE_COMMAND, modules.get(0).getId()))
                        .build()));

        for (int i = 1; i < modules.size(); i++) {
            buttons.add(
                    List.of(
                            InlineKeyboardButton.builder()
                                    .text(modules.get(i).getName())
                                    .callbackData(format("%s:%s", FIND_MODULE_COMMAND, modules.get(i).getId()))
                                    .build()
                    ));
        }

        return buttons;
    }
}
