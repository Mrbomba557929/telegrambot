package com.app.telegrambot.domain.entity;

import com.app.telegrambot.domain.entity.ref.WordTranslationRef;
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
@Table(value = "words")
public class WordEntity {

    @Id
    @Column(value = "word")
    private String word;

    @MappedCollection(idColumn = "word")
    private Set<WordTranslationRef> translations;
}
