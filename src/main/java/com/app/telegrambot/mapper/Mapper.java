package com.app.telegrambot.mapper;

public interface Mapper<E, D> {

    /**
     * Converts the dto to the entity.
     *
     * @param dto - the dto.
     * @return the entity.
     */
    E toEntity(D dto);

    /**
     * Converts the entity to the dto.
     *
     * @param entity - the entity.
     * @return the dto.
     */
    D toDto(E entity);
}
