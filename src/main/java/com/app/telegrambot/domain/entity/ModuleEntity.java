package com.app.telegrambot.domain.entity;

import com.app.telegrambot.domain.entity.ref.UserWordRef;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(value = "modules")
public class ModuleEntity {

    @Id
    @Column(value = "id")
    private int id;

    @Column(value = "name")
    private String name;

    @Column(value = "created_at")
    private Instant createdAt;

    private Set<UserWordRef> words;
}
