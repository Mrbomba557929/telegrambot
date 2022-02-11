package com.app.telegrambot.repository;

import com.app.telegrambot.domain.entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query(value = """
            SELECT
                CASE WHEN EXISTS
                (
                    SELECT *
                    FROM users
                    WHERE users.id = :id
                )
                THEN true
                ELSE false
            END
            """)
    boolean existsById(@Param("id") Integer id);

    @Query(value = """
            WITH e AS (
                INSERT INTO users (id, first_name, last_name, username, is_bot, language_code)
                VALUES (:id, :firstName, :lastName, :username, :isBot, :languageCode)
                ON CONFLICT ON CONSTRAINT users_pkey DO NOTHING
                RETURNING *
            )
            SELECT *
            FROM e
            """)
    UserEntity save(@Param("id") Integer id, @Param("firstName") String firstName,
                    @Param("lastName") String lastName, @Param("username") String username,
                    @Param("isBot") boolean isBot, @Param("languageCode") String languageCode);
}
