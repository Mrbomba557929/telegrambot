package com.app.telegrambot.domain.entity;

import com.app.telegrambot.domain.entity.ref.WordRef;
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
public class TranslationEntity {

    @Id
    @Column(value = "translation")
    private String translation;

    @MappedCollection(idColumn = "translation")
    private Set<WordRef> words;
}
