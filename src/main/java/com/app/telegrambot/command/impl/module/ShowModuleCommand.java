package com.app.telegrambot.command.impl.module;

import com.app.telegrambot.command.Command;
import com.app.telegrambot.domain.entity.ModuleEntity;
import com.app.telegrambot.domain.еnum.ParseMode;
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

@Slf4j
@RequiredArgsConstructor
@Component
public class ShowModuleCommand implements Command {

    public static final String DELIMITER = ":";
    public static final String DOT = "·";
    public static final String ASK_FOR_NAME_MODULE = "Назови имя модуля";
    public static final String SELECTED_MODULE_REGEX = DOT + ".+" + DOT;

    private final ModuleService moduleService;
    private final EditMessageTextSender editMessageTextSender;
    private final MessageSender messageSender;
    private final StateMachine stateMachine;

    @Override
    public void execute(Update update) {
        try {

            if (update.hasCallBackQuery()) {
                Long moduleId = Long.parseLong(update.message().text().split(DELIMITER)[1]);
                editMessageTextSender.send(EditMessageText.builder()
                        .chatId(update.message().chat().id())
                        .messageId(Math.toIntExact(update.message().id()))
                        .parseMode(ParseMode.MARKDOWN)
                        .text(moduleService.findById(moduleId).toString())
                        .replyMarkup(tagSelectedModule(update.message().replyMarkup(), moduleId))
                        .build());
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
            log.error("An error occurred {}", e.getMessage());
        }
    }

    private void askForNameModule(Update update) {
        try {
            ModuleEntity module = moduleService.findByNameAndUserId(update.message().text(), update.message().from().idLong());

            messageSender.send(SendMessage.builder()
                    .text(module.toString())
                    .chatId(update.message().chat().id())
                    .parseMode(ParseMode.MARKDOWN)
                    .build());

            stateMachine.stop(update.message().from().idLong());
        } catch (TelegramApiException e) {
            log.error("An error occurred {}", e.getMessage());
        }
    }

    private InlineKeyboardMarkup tagSelectedModule(InlineKeyboardMarkup inlineKeyboardMarkup, Long moduleId) {
        List<List<InlineKeyboardButton>> inlineKeyboard = inlineKeyboardMarkup.getInlineKeyboard();

        for (int i = 0; i < inlineKeyboard.size() - 1; i++) {
            InlineKeyboardButton button = inlineKeyboard.get(i).get(0);

            if (button.getText().matches(SELECTED_MODULE_REGEX)) {
                button.setText(button.getText().substring(1, button.getText().length() - 1));
            }

            if (Long.parseLong(button.getCallbackData().split(DELIMITER)[1]) == moduleId) {
                button.setText(DOT + button.getText() + DOT);
            }
        }

        inlineKeyboardMarkup.setInlineKeyboard(inlineKeyboard);

        return inlineKeyboardMarkup;
    }
}
