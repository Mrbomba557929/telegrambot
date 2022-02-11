package com.app.telegrambot.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(value = "users")
public class UserEntity {

    @Id
    @Column(value = "id")
    private int id;

    @Column(value = "first_name")
    private String firstName;

    @Column(value = "last_name")
    private String lastName;

    @Column(value = "username")
    private String username;

    @Column(value = "language_code")
    private String languageCode;

    @Column(value = "is_bot")
    private boolean isBot;

    @MappedCollection(idColumn = "user_id")
    private Set<ModuleEntity> modules;
}
