package com.app.telegrambot.domain.module.entity;

import com.app.telegrambot.domain.module.entity.ref.WordTranslationRef;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(value = "words")
public class WordEntity {

    @Id
    @Column(value = "word")
    private String word;

    @MappedCollection(idColumn = "word", keyColumn = "translate")
    private Set<WordTranslationRef> translations;

    public void addTranslate(TranslateEntity translate) {

        if (Objects.isNull(translations)) {
            translations = new HashSet<>();
        }

        WordTranslationRef wordTranslationRef = WordTranslationRef.builder()
                        .word(AggregateReference.to(this.word))
                        .translate(AggregateReference.to(translate.getTranslate()))
                        .build();

        translations.add(wordTranslationRef);
    }
}
