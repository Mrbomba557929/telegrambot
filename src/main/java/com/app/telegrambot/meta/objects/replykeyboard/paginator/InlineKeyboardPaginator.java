package com.app.telegrambot.meta.objects.replykeyboard.paginator;

import com.app.telegrambot.meta.objects.replykeyboard.InlineKeyboardMarkup;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.lang.String.format;

@Component
public class InlineKeyboardPaginator {

    public static final Integer NUMBER_OF_PAGES_IN_BLOCK = 5;
    public static final String GREATER_SIGN = "›";
    public static final String LESS_SIGN = "‹";
    public static final String QUOTE_RIGHT = "»";
    public static final String QUOTE_LEFT = "«";
    public static final String DOT = "·";

    public InlineKeyboardMarkup paginate(int totalPages, int currentPage, String dataPattern) {

        if (totalPages < 0 || currentPage < 0) {
            return new InlineKeyboardMarkup();
        }

        return InlineKeyboardMarkup.builder()
                .withRow(generateButtons(totalPages, currentPage, dataPattern))
                .build();
    }

    private List<InlineKeyboardButton> generateButtons(int totalPages, int currentPage, String dataPattern) {

        if (totalPages <= NUMBER_OF_PAGES_IN_BLOCK) {
            return generateSingleBlock(totalPages, currentPage, dataPattern);
        } else if (currentPage < 4) {
            return generateFirstBlock(totalPages, currentPage, dataPattern);
        } else if (currentPage <= totalPages - 4) {
            return generateMiddleBlock(totalPages, currentPage, dataPattern);
        }

        return generateLastBlock(totalPages, currentPage, dataPattern);
    }

    private List<InlineKeyboardButton> generateSingleBlock(int totalPages, int currentPage, String dataPattern) {
        AtomicInteger counter = new AtomicInteger(1);
        return Stream.generate(() -> InlineKeyboardButton.builder()
                        .callbackData(format(dataPattern, counter.get()))
                        .text(counter.get() == currentPage ?
                                DOT + counter.getAndIncrement() + DOT :
                                String.valueOf(counter.getAndIncrement()))
                        .build())
                .limit(totalPages)
                .toList();
    }

    private List<InlineKeyboardButton> generateFirstBlock(int totalPages, int currentPage, String dataPattern) {
        AtomicInteger counter = new AtomicInteger(1);
        return Stream.concat(
                Stream.generate(() -> {
                    String text;

                    if (counter.get() != 4) {
                        text = counter.get() == currentPage ?
                                DOT + counter.get() + DOT :
                                String.valueOf(counter.get());
                    } else {
                        text = counter.get() + GREATER_SIGN;
                    }

                    return InlineKeyboardButton.builder()
                            .callbackData(format(dataPattern, counter.getAndIncrement()))
                            .text(text)
                            .build();
                }).limit(4),
                Stream.of(InlineKeyboardButton.builder()
                        .callbackData(format(dataPattern, totalPages))
                        .text(totalPages + QUOTE_RIGHT)
                        .build())
        ).toList();
    }

    private List<InlineKeyboardButton> generateMiddleBlock(int totalPages, int currentPage, String dataPattern) {
        return List.of(
                InlineKeyboardButton.builder()
                        .callbackData(format(dataPattern, 1))
                        .text(QUOTE_LEFT + 1)
                        .build(),
                InlineKeyboardButton.builder()
                        .callbackData(format(dataPattern, currentPage - 1))
                        .text(LESS_SIGN + (currentPage - 1))
                        .build(),
                InlineKeyboardButton.builder()
                        .callbackData(format(dataPattern, currentPage))
                        .text(DOT + currentPage + DOT)
                        .build(),
                InlineKeyboardButton.builder()
                        .callbackData(format(dataPattern, currentPage + 1))
                        .text((currentPage + 1) + GREATER_SIGN)
                        .build(),
                InlineKeyboardButton.builder()
                        .callbackData(format(dataPattern, totalPages))
                        .text(totalPages + QUOTE_RIGHT)
                        .build());
    }

    private List<InlineKeyboardButton> generateLastBlock(int totalPages, int currentPage, String dataPattern) {
        AtomicInteger counter = new AtomicInteger(totalPages - 2);
        return Stream.concat(
                Stream.of(
                        InlineKeyboardButton.builder()
                                .callbackData(format(dataPattern, 1))
                                .text(QUOTE_LEFT + 1)
                                .build(),
                        InlineKeyboardButton.builder()
                                .callbackData(format(dataPattern, totalPages - 3))
                                .text(LESS_SIGN + (totalPages - 3))
                                .build()
                ),
                Stream.generate(() -> InlineKeyboardButton.builder()
                        .callbackData(format(dataPattern, counter.get()))
                        .text(counter.get() == currentPage ?
                                DOT + counter.getAndIncrement() + DOT :
                                String.valueOf(counter.getAndIncrement()))
                        .build())
        ).toList();
    }
}
