package com.app.telegrambot.repository;

import com.app.telegrambot.domain.module.entity.TranslateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslateRepository extends CrudRepository<TranslateEntity, String> {
}
