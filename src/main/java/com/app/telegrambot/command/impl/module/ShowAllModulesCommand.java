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

/**
 * {@link Command} shows all modules.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ShowAllModulesCommand implements Command {

    public static final Integer NUMBER_OF_PAGES = 5;
    public static final Integer INITIAL_PAGE_SIZE = 1;

    private final MessageSender messageSender;
    private final EditMessageTextSender editMessageTextSender;
    private final ModuleService moduleService;
    private final InlineKeyboardPaginator inlineKeyboardPaginator;

    /*
    TODO: Переработать полностью логику, исправить пагинацию.
          Когда, к примеру, у нас всего 2 модуля и всё.
          Придумать что то с callback и командами.
          Есть ошибки с символами, почему то телеграм не переводит текст в символ.
          Проверить работу popup.
          Исправить, чтобы бот не каждый раз выводил сообщение, а изменял предыдущее.
     */

    @Override
    public void execute(Update update) {
        try {
            InlineKeyboardMarkup.Builder inlineKeyboardMarkup = InlineKeyboardMarkup.builder();

            if (!update.hasCallBackQuery()) {
                Page<ModuleEntity> modules = moduleService.findAll(INITIAL_PAGE_SIZE, NUMBER_OF_PAGES);

                modules.forEach(module -> inlineKeyboardMarkup.withRow(
                        List.of(
                                InlineKeyboardButton.builder()
                                        .text(module.getName())
                                        .callbackData(String.valueOf(module.getId()))
                                        .build()
                        )));

                inlineKeyboardMarkup.zip(inlineKeyboardPaginator
                        .paginate(modules.getTotalPages(), INITIAL_PAGE_SIZE, "%d")
                        .getInlineKeyboard());

                // TODO: Придумать с выводов информации о конкретном модуле. То, что я написал - неправильно!

                messageSender.send(SendMessage.builder()
                        .text(modules.getContent().get(0).toString())
                        .chatId(update.message().chat().id())
                        .parseMode(ParseMode.MARKDOWN)
                        .replyMarkup(inlineKeyboardMarkup.build())
                        .build());
            } else {
                int currentPage = Integer.parseInt(update.message().text());
                Page<ModuleEntity> modules = moduleService.findAll(currentPage, NUMBER_OF_PAGES);

                modules.forEach(module -> inlineKeyboardMarkup.withRow(
                        List.of(
                                InlineKeyboardButton.builder()
                                        .text(module.getName())
                                        .callbackData(String.valueOf(module.getId()))
                                        .build()
                        )));

                inlineKeyboardMarkup.zip(inlineKeyboardPaginator
                        .paginate(modules.getTotalPages(), INITIAL_PAGE_SIZE, "%d")
                        .getInlineKeyboard());

                editMessageTextSender.send(EditMessageText.builder()
                        .chatId(update.message().chat().id())
                        .messageId(Math.toIntExact(update.message().id()))
                        .text(modules.getContent().get(0).toString())
                        .parseMode(ParseMode.MARKDOWN)
                        .replyMarkup(inlineKeyboardMarkup.build())
                        .build());
            }

        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }

    private List<List<InlineKeyboardButton>> translateModulesIntoInlineKeyboardButtons(List<ModuleEntity> modules) {
        return modules.stream()
                .map(module -> List.of(InlineKeyboardButton.builder()
                        .text(module.getName())
                        .callbackData(String.valueOf(module.getId()))
                        .build())
                ).toList();
    }
}
