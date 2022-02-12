package com.app.telegrambot.repository;

import com.app.telegrambot.domain.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Integer> {

    @Query(value = """
           SELECT words.word
           FROM words
           WHERE words.word = :word
           """, nativeQuery = true)
    Optional<WordEntity> findByWord(@Param("word") String word);
}
