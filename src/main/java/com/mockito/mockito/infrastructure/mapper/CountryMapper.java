package com.mockito.mockito.infrastructure.mapper;

import com.mockito.mockito.domain.entity.Country;
import com.mockito.mockito.infrastructure.database.entity.CountryEntity;

import java.util.Optional;
public final class CountryMapper implements Mapper<Country, CountryEntity> {

    @Override
    public Optional<Country> toDomain(CountryEntity entity) {
        return entity == null ? Optional.empty() : Optional.of(Country.builder()
                .id(entity.getId())
                .version(entity.getVersion())
                .created(entity.getCreated())
                .updated(entity.getUpdated())
                .setName(entity.getName())
                .setCode(entity.getCode())
                .build());
    }

    @Override
    public CountryEntity toEntity(Country domain) {
        return domain == null ? null : CountryEntity.builder()
                .id(domain.getId())
                .version(domain.getVersion())
                .created(domain.getCreated())
                .updated(domain.getUpdated())
                .name(domain.getName())
                .code(domain.getCode())
                .build();
    }

}