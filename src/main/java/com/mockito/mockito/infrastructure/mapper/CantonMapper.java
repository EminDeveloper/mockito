package com.mockito.mockito.infrastructure.mapper;

import com.mockito.mockito.domain.entity.Canton;
import com.mockito.mockito.infrastructure.database.entity.CantonEntity;

import java.util.Optional;


public final class CantonMapper implements Mapper<Canton, CantonEntity> {

    private final CountryMapper countryMapper;

    public CantonMapper(CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    @Override
    public Optional<Canton> toDomain(CantonEntity entity) {
        return entity == null ? Optional.empty() : Optional.of(Canton.builder()
                .id(entity.getId())
                .version(entity.getVersion())
                .created(entity.getCreated())
                .updated(entity.getUpdated())
                .setName(entity.getName())
                .setAbbreviation(entity.getAbbreviation())
                .build());
    }

    @Override
    public CantonEntity toEntity(Canton canton) {
        return canton == null ? null : CantonEntity.builder()
                .id(canton.getId())
                .version(canton.getVersion())
                .created(canton.getCreated())
                .updated(canton.getUpdated())
                .name(canton.getName())
                .abbreviation(canton.getAbbreviation())
                .country(countryMapper.toEntity(canton.getCountry()))
                .build();
    }

}