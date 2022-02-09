package com.app.telegrambot.domain.entity.ref;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "words_translations")
@Data
@Builder
public class TranslationRef {
    private String translation;
    private Integer userId;
}