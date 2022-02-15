package com.app.telegrambot.repository;

import com.app.telegrambot.domain.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, Long> {

    @Query(nativeQuery = true, value = """
    SELECT *
    FROM modules
    WHERE modules.name = ?1 AND modules.user_id = ?2
    """)
    Optional<ModuleEntity> findByNameAndUserId(String name, Long userId);
}
