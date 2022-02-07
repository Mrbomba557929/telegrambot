package com.app.telegrambot.repository;

import com.app.telegrambot.domain.module.entity.WordEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends CrudRepository<WordEntity, String> {

    @Modifying
    @Query(value = "INSERT INTO words VALUES (:word)")
    void save(@Param("word") String word);

    @Query(value = """
            SELECT *
            FROM words
            INNER JOIN words_translations wt on words.word = wt.word
            INNER JOIN users u on u.id = wt.user_id
            INNER JOIN translations t on t.translate = wt.translate
            WHERE words.word = :word
            """)
    WordEntity findByWord(@Param("word") String word);
}
