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

import java.util.List;

import static java.lang.String.format;

/**
 * {@link Command} shows all modules.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ShowAllModulesCommand implements Command {

    public static final Integer NUMBER_OF_PAGES = 5;
    public static final Integer INITIAL_PAGE_SIZE = 1;
    public static final String FIND_ALL_MODULES_COMMAND = "/modules";
    public static final String FIND_ALL_MODULES_WITH_CURRENT_PAGE_REGEX = "/modules:\\d+";
    public static final String FIND_MODULE_COMMAND = "/module";
    public static final String DELIMITER = ":";

    private final MessageSender messageSender;
    private final EditMessageTextSender editMessageTextSender;
    private final ModuleService moduleService;
    private final InlineKeyboardPaginator inlineKeyboardPaginator;

    @Override
    public void execute(Update update) {
        try {
            InlineKeyboardMarkup.Builder inlineKeyboardMarkup = InlineKeyboardMarkup.builder();
            int page = update.message().text().matches(FIND_ALL_MODULES_WITH_CURRENT_PAGE_REGEX) ?
                    INITIAL_PAGE_SIZE :
                    Integer.parseInt(update.message().text().split(DELIMITER)[1]);

            Page<ModuleEntity> modules = moduleService.findAll(page, NUMBER_OF_PAGES);

            modules.forEach(module -> inlineKeyboardMarkup.withRow(
                    List.of(
                            InlineKeyboardButton.builder()
                                    .text(module.getName())
                                    .callbackData(format("%s:%s", FIND_MODULE_COMMAND, module.getId()))
                                    .build()
                    )
            ));

            inlineKeyboardMarkup.zip(inlineKeyboardPaginator
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
}
