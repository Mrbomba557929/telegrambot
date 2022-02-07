package com.app.telegrambot.domain.module.entity;

import com.app.telegrambot.domain.module.entity.ref.WordTranslationRef;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(value = "translations")
public class TranslateEntity {

    @Id
    @Column(value = "translate")
    private String translate;

    @MappedCollection(idColumn = "translate", keyColumn = "word")
    private Set<WordTranslationRef> words;
}
