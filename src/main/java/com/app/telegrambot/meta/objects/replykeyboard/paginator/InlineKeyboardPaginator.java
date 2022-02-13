package com.app.telegrambot.meta.objects.replykeyboard.paginator;

import com.app.telegrambot.meta.objects.replykeyboard.InlineKeyboardMarkup;
import com.app.telegrambot.meta.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;
import static java.lang.String.format;

@Component
public class InlineKeyboardPaginator {

    public static final String CAN_NOT_GO_FURTHER = "NO";
    public static final String NEXT_BLOCK = "&raquo;";
    public static final String PREV_BLOCK = "&laquo;";
    public static final String PREV_DATA = "/m:prev:%d:%d:%d";
    public static final String NEXT_DATA = "/m:next:%d:%d:%d";
    public static final String PAGE_DATA = "/m:%d:%d:%d:%d";
    public static final String CANNOT_BE_SWITCHED = "&times;";
    public static final String DOT = "\t&#8226;";

    public InlineKeyboardMarkup paginate(int currentPage, int totalPages, int totalPagesInBlock,
                                         int numberOfBlock, int firstPage, int lastPage) {
        return InlineKeyboardMarkup.builder()
                .withRow(generatePages(currentPage, totalPages, totalPagesInBlock, numberOfBlock, firstPage, lastPage))
                .build();
    }

    private List<InlineKeyboardButton> generatePages(int currentPage, int totalPages, int totalPagesInBlock,
                                                     int numberOfBlock, int firstPage, int lastPage) {
        List<InlineKeyboardButton> pages = new ArrayList<>();

        String prev = numberOfBlock == 1 ?
                CAN_NOT_GO_FURTHER :
                format(PREV_DATA, numberOfBlock - 1, firstPage - 3, firstPage - 1);

        String next = (numberOfBlock + 1) > ceil((double) totalPages / (double) totalPagesInBlock) ?
                CAN_NOT_GO_FURTHER :
                format(NEXT_DATA, numberOfBlock + 1, lastPage + 1,
                        (numberOfBlock + 1) == ceil((double) totalPages / (double) totalPagesInBlock) ?
                                totalPages :
                                lastPage + 3);

        pages.add(InlineKeyboardButton.builder()
                .text(prev.equals(CAN_NOT_GO_FURTHER) ? CANNOT_BE_SWITCHED : PREV_BLOCK)
                .callbackData(prev)
                .build());

        for (int i = firstPage; i <= lastPage; i++) {
            pages.add(InlineKeyboardButton.builder()
                    .text(i == currentPage ? (DOT + i + DOT) : (String.valueOf(i - 1)))
                    .callbackData(format(PAGE_DATA, i - 1, numberOfBlock, firstPage, lastPage))
                    .build());
        }

        pages.add(InlineKeyboardButton.builder()
                .text(next.equals(CAN_NOT_GO_FURTHER) ? CANNOT_BE_SWITCHED : NEXT_BLOCK)
                .callbackData(next)
                .build());

        return pages;
    }
}
