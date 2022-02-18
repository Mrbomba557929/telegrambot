package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.domain.еnum.ParseMode;
import com.app.telegrambot.fms.PartOfState;
import com.app.telegrambot.fms.State;
import com.app.telegrambot.fms.StateMachine;
import com.app.telegrambot.meta.exception.compiletime.impl.TelegramApiException;
import com.app.telegrambot.meta.methods.send.impl.EditMessageTextSender;
import com.app.telegrambot.meta.methods.send.impl.MessageSender;
import com.app.telegrambot.meta.methods.send.objects.EditMessageText;
import com.app.telegrambot.meta.methods.send.objects.SendMessage;
import com.app.telegrambot.meta.objects.Update;
import com.app.telegrambot.meta.objects.replykeyboard.InlineKeyboardMarkup;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.InlineKeyboardButton;
import com.app.telegrambot.service.ModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor
@Component
public class ShowModuleCommand implements Command {

    public static final String DELIMITER = ":";
    public static final String DOT = "·";
    public static final String ASK_FOR_NAME_MODULE = "Назови имя модуля";
    public static final String SELECTED_MODULE_REGEX = DOT + ".+" + DOT;

    public static final String REGEX = "/module(:[a-zA-Z0-9]+)+";
    public static final String SHOW_ALL_MODULES= "showAllModules";
    public static final String SHOW_MODULE = "showModule";

    private final ModuleService moduleService;
    private final EditMessageTextSender editMessageTextSender;
    private final MessageSender messageSender;
    private final StateMachine stateMachine;

    @Override
    public void execute(Update update) {
        try {

            if (update.hasCallBackQuery() && update.message().text().matches(REGEX)) {
                editMessage(update);
                return;
            }

            messageSender.send(SendMessage.builder()
                    .text(ASK_FOR_NAME_MODULE)
                    .parseMode(ParseMode.MARKDOWN)
                    .chatId(update.message().chat().id())
                    .build());

            stateMachine.addState(update.message().from().idLong(), State.builder()
                    .transition(this::askForNameModule)
                    .build());

        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при отправке запроса: {}", e.getMessage());
        }
    }

    private void editMessage(Update update) throws TelegramApiException {
        Long moduleId = Long.parseLong(update.message().text().split(DELIMITER)[1]);

        InlineKeyboardMarkup inlineKeyboardMarkup;

        if (update.message().text().endsWith(SHOW_ALL_MODULES)) {
            inlineKeyboardMarkup = markSelectedModuleAndReturnKeyboard(update.message().replyMarkup(), moduleId);
        } else if (update.message().text().endsWith(SHOW_MODULE)) {
            inlineKeyboardMarkup = generateKeyboard();
        } else {
            inlineKeyboardMarkup = update.message().replyMarkup();
        }

        editMessageTextSender.send(EditMessageText.builder()
                .chatId(update.message().chat().id())
                .messageId(Math.toIntExact(update.message().id()))
                .parseMode(ParseMode.MARKDOWN)
                .text(moduleService.findById(moduleId).toString())
                .replyMarkup(inlineKeyboardMarkup)
                .build());
    }

    @PartOfState
    private void askForNameModule(Update update) {
        try {

            ModuleEntity module = moduleService.findByNameAndUserId(update.message().text(), update.message().from().idLong());

            messageSender.send(SendMessage.builder()
                    .text(module.toString())
                    .chatId(update.message().chat().id())
                    .parseMode(ParseMode.MARKDOWN)
                    .replyMarkup(generateKeyboard())
                    .build());

        } catch (TelegramApiException e) {
            log.error("Произошла ошибка при отправке запроса: {}", e.getMessage());
        } finally {
            stateMachine.stop(update.message().from().idLong());
        }
    }

    private InlineKeyboardMarkup markSelectedModuleAndReturnKeyboard(InlineKeyboardMarkup inlineKeyboardMarkup, Long moduleId) {
        List<List<InlineKeyboardButton>> inlineKeyboard = inlineKeyboardMarkup.getInlineKeyboard();

        for (int i = 0; i < inlineKeyboard.size() - 2; i++) {
            InlineKeyboardButton button = inlineKeyboard.get(i).get(0);

            if (button.getText().matches(SELECTED_MODULE_REGEX)) {
                button.setText(button.getText().substring(1, button.getText().length() - 1));
            }

            if (Long.parseLong(button.getCallbackData().split(DELIMITER)[1]) == moduleId) {
                button.setText(DOT + button.getText() + DOT);
            }
        }

        inlineKeyboard.get(inlineKeyboard.size() - 1).get(0).setCallbackData(format("/module:%d:showModule", moduleId));

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup generateKeyboard() {
        return InlineKeyboardMarkup.builder()
                .withRow(List.of(
                        InlineKeyboardButton.builder()
                                .text("Добавить слова")
                                .callbackData("/addWords")
                                .build(),
                        InlineKeyboardButton.builder()
                                .text("Удалить модуль")
                                .callbackData("/deleteModule")
                                .build()
                ))
                .withRow(List.of(
                        InlineKeyboardButton.builder()
                                .text("Меню")
                                .callbackData("/menu:previous")
                                .build()
                ))
                .build();
    }
}
