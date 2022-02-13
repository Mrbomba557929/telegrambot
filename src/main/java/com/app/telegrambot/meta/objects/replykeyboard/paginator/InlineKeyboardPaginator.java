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

    public static final String CAN_NOT_GO_FURTHER = "no";

    public InlineKeyboardMarkup paginate(int currentPage, int totalPages, int totalPagesInBlock, int numberOfBlock,
                                             int firstPage, int lastPage) {
        return InlineKeyboardMarkup.builder()
                .withRow(generatePages(currentPage, totalPages, totalPagesInBlock, numberOfBlock, firstPage, lastPage))
                .build();
    }

    private List<InlineKeyboardButton> generatePages(int currentPage, int totalPages, int totalPagesInBlock, int numberOfBlock,
                                                     int firstPage, int lastPage) {
        List<InlineKeyboardButton> pages = new ArrayList<>();

        String prev = numberOfBlock == 1 ?
                CAN_NOT_GO_FURTHER :
                format("prev:%d:%d:%d", numberOfBlock - 1, firstPage - 3, firstPage - 1);

        String next = (numberOfBlock + 1) > ceil((double) totalPages / (double) totalPagesInBlock) ?
                CAN_NOT_GO_FURTHER :
                format("next:%d:%d:%d", numberOfBlock + 1, lastPage + 1,
                        (numberOfBlock + 1) == ceil((double) totalPages / (double) totalPagesInBlock) ?
                                totalPages :
                                lastPage + 3);

        pages.add(InlineKeyboardButton.builder()
                .text(prev.equals(CAN_NOT_GO_FURTHER) ? "&times;" : "&laquo;")
                .callbackData(prev)
                .build());

        for (int i = firstPage; i <= lastPage; i++) {
            String text;

            if (i == currentPage) {
                text = "\t&#8226;" + i + "\t&#8226;";
            } else {
                text = String.valueOf(i - 1);
            }

            pages.add(InlineKeyboardButton.builder()
                    .text(text)
                    .callbackData(format("%d:%d:%d:%d", i - 1, numberOfBlock, firstPage, lastPage))
                    .build());
        }

        pages.add(InlineKeyboardButton.builder()
                .text(next.equals(CAN_NOT_GO_FURTHER) ? "&times;" : "&raquo;")
                .callbackData(next)
                .build());

        return pages;
    }
}
