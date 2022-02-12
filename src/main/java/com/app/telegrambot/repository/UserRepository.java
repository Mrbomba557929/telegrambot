package com.app.telegrambot.repository;

import com.app.telegrambot.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

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
            """, nativeQuery = true)
    boolean existsById(@Param("id") Integer id);
}
