package com.app.telegrambot.repository;

import com.app.telegrambot.domain.entity.WordEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends CrudRepository<WordEntity, String> {

    @Query(value = """
           SELECT words.word
           FROM words
           WHERE words.word = :word
           """)
    Optional<WordEntity> findByWord(@Param("word") String word);
}
