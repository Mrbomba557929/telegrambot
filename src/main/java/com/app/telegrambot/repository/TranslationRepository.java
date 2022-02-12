package com.app.telegrambot.repository;

import com.app.telegrambot.domain.entity.TranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslationRepository extends JpaRepository<TranslationEntity, Integer> {

    @Query(value = """
            SELECT translations.translate
            FROM translations
            WHERE translations.translate = :translation
            """, nativeQuery = true)
    Optional<TranslationEntity> findByTranslation(@Param("translation") String translation);
}
