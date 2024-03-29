package com.mockito.mockito.infrastructure.mapper;

import com.mockito.mockito.domain.entity.BaseDomain;
import com.mockito.mockito.infrastructure.database.entity.BaseEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public interface Mapper<D extends BaseDomain, E extends BaseEntity> {

    default Optional<D> toDomain(Optional<E> entity) {
        return entity.isEmpty() ? Optional.empty() : toDomain(entity.get());
    }

    Optional<D> toDomain(E entity);

    default Set<D> toDomain(Collection<E> entities) {
        return entities == null ? Collections.emptySet() : entities.stream()
                .map(this::toDomain)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    E toEntity(D domain);

    default Set<E> toEntity(Collection<D> domains) {
        return domains == null ? Collections.emptySet() : domains.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

}