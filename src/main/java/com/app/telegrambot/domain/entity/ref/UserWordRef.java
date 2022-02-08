package com.app.telegrambot.domain.entity.ref;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "modules_words")
@Data
@Builder
public class UserWordRef {
    private String word;
}
