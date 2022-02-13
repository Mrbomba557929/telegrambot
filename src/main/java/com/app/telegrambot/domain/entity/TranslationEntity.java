package com.app.telegrambot.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "translations")
public class TranslationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "translation")
    private String translation;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "words_translations",
            joinColumns = @JoinColumn(name = "translation_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    private Set<WordEntity> words;
}
