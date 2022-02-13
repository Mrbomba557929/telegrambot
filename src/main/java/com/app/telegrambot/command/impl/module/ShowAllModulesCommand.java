package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.methods.send.impl.AnswerCallbackQuerySender;
import com.app.telegrambot.meta.methods.send.objects.AnswerCallbackQuery;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
import com.app.telegrambot.meta.methods.send.objects.SendMessage;
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

    public static final Integer NUMBER_OF_ELEMENTS = 5;
    public static final Integer TOTAL_PAGES_IN_BLOCK = 3;
    public static final String CAN_NOT_GO_FURTHER = "NO";
    public static final String ANSWER_CALLBACK_QUERY = "Дядя, ты слепой что - ли? Ты не можешь так сделать!";
    public static final String[] INITIAL_PAGE_VALUE = new String[] {"1", "1", "1", "3"};
    public static final String SEPARATOR = ":";

    private final MessageSender sender;
    private final AnswerCallbackQuerySender answerCallbackQuerySender;
    private final ModuleService moduleService;
    private final InlineKeyboardPaginator inlineKeyboardPaginator;

    @Override
    public void execute(Update update) {
        try {

            if (update.message().text().equalsIgnoreCase(CAN_NOT_GO_FURTHER)) {
                answerCallbackQuerySender.send(AnswerCallbackQuery.builder()
                        .callbackQueryId(update.callbackQuery().id())
                        .text(ANSWER_CALLBACK_QUERY)
                        .showAlert(false)
                        .build());
                return;
            }

            showModules(update, update.message().text().equals("/m") ?
                    INITIAL_PAGE_VALUE :
                    update.message().text().split(SEPARATOR));

        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }

    private void showModules(Update update, String[] args) {
        try {
            int numberOfBlock = Integer.parseInt(args[2]);
            int firstPage = Integer.parseInt(args[3]);
            int lastPage = Integer.parseInt(args[4]);

            Page<ModuleEntity> modules;

            if (args[1].equals("next") || args[1].equals("prev")) {
                modules = moduleService.findAll(firstPage, NUMBER_OF_ELEMENTS);
            } else {
                modules = moduleService.findAll(Integer.parseInt(args[1]), NUMBER_OF_ELEMENTS);
            }

            sender.send(SendMessage.builder()
                    .text(translateModulesIntoTextRepresentation(modules.getContent()))
                    .chatId(update.message().chat().id())
                    .replyMarkup(inlineKeyboardPaginator.paginate(
                            modules.getNumber(), modules.getTotalPages(), TOTAL_PAGES_IN_BLOCK,
                            numberOfBlock, firstPage, lastPage))
                    .build());

        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }

    private String translateModulesIntoTextRepresentation(List<ModuleEntity> modules) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < modules.size(); i++) {
            sb.append(i).append(1).append(".").append("\n").append(modules.get(i).toString()).append("\n\n");
        }

        return sb.toString();
    }
}
