package com.app.telegrambot.repository;

import com.app.telegrambot.domain.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, Long> {

    @Query(nativeQuery = true, value = """
    SELECT *
    FROM modules
    WHERE
        modules.name = ?1 AND
        modules.user_id = ?2
    """)
    Optional<ModuleEntity> findByNameAndUserId(String name, Long userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = """
    DELETE FROM modules
    WHERE
        modules.name = ?1 AND
        modules.user_id = ?2
    """)
    void deleteByNameAndUserId(String name, Long userId);

    @Query(value = """
            SELECT
                CASE WHEN EXISTS
                (
                    SELECT *
                    FROM modules
                    WHERE
                        modules.name = ?1 AND
                        modules.user_id = ?2
                )
                THEN true
                ELSE false
            END
            """, nativeQuery = true)
    boolean existsByNameAndUserId(String name, Long userId);
}
