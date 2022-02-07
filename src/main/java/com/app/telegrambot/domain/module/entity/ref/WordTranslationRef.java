package com.app.telegrambot.domain.module.entity.ref;

import com.app.telegrambot.domain.module.entity.TranslateEntity;
import com.app.telegrambot.domain.module.entity.UserEntity;
import com.app.telegrambot.domain.module.entity.WordEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "words_translations")
@Data
@Builder
public class WordTranslationRef {
    private AggregateReference<WordEntity, String> word;
    private AggregateReference<TranslateEntity, String> translate;
    private AggregateReference<UserEntity, Integer> userId;
}
