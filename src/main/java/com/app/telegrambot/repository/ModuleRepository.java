package com.app.telegrambot.repository;

import com.app.telegrambot.domain.entity.ModuleEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface ModuleRepository extends CrudRepository<ModuleEntity, Integer> {

    @Query(value = """
            WITH e(id, name, created_at, user_id) AS (
                INSERT INTO modules (name, created_at, user_id)
                VALUES
                    (:name, :createdAt, :userId)
                RETURNING id, name, created_at, user_id
            )
            SELECT *
            FROM e
            UNION
            SELECT *
            FROM modules
            WHERE modules.id = e.id
            """)
    ModuleEntity save(@Param("name") String name, @Param("createdAt") Instant createdAt, @Param("userId") Integer userId);
}
