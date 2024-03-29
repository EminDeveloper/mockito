package com.mockito.mockito.infrastructure.mapper;

import com.mockito.mockito.domain.entity.City;
import com.mockito.mockito.infrastructure.database.entity.CityEntity;

import java.util.Optional;


public final class CityMapper implements Mapper<City, CityEntity> {

    private final CantonMapper cantonMapper;

    public CityMapper(CantonMapper cantonMapper) {
        this.cantonMapper = cantonMapper;
    }

    @Override
    public Optional<City> toDomain(CityEntity entity) {
        return entity == null ? Optional.empty() : Optional.of(City.builder()
                .id(entity.getId())
                .version(entity.getVersion())
                .created(entity.getCreated())
                .updated(entity.getUpdated())
                .setName(entity.getName())
                .build());
    }

    @Override
    public CityEntity toEntity(City city) {
        return city == null ? null : CityEntity.builder()
                .id(city.getId())
                .version(city.getVersion())
                .created(city.getCreated())
                .updated(city.getUpdated())
                .name(city.getName())
                .canton(cantonMapper.toEntity(city.getCanton()))
                .build();
    }

}
