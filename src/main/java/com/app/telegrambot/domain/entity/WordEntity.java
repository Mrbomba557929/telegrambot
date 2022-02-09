package com.app.telegrambot.domain.entity;

import com.app.telegrambot.domain.entity.ref.TranslationRef;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
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
    private Set<TranslationRef> translations;

    /**
     A list of translations that belong to the word but have not yet been saved to the database.
     It is expected that these translations will be saved later.
     */
    @Persistent
    private List<TranslationEntity> unsavedTranslationsEntities;
}
