package com.app.telegrambot.repository;

import com.app.telegrambot.domain.entity.TranslationEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslationRepository extends CrudRepository<TranslationEntity, String> {

    @Query(value = """
            SELECT translations.translate
            FROM translations
            WHERE translations.translate = :translation
            """)
    Optional<TranslationEntity> findByTranslation(@Param("translation") String translation);
}
