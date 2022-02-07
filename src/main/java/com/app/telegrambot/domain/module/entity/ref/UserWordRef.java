package com.app.telegrambot.domain.module.entity.ref;

import com.app.telegrambot.domain.module.entity.WordEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "modules_words")
@Data
@Builder
public class UserWordRef {
    private AggregateReference<WordEntity, String> word;
}
