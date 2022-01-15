package com.mockito.mockito.infrastructure.database.repository;

import com.mockito.mockito.infrastructure.database.entity.CityEntity;

import java.util.List;
import java.util.Optional;


public interface CityEntityRepository extends BaseEntityRepository<CityEntity> {

    Optional<CityEntity> findByNameIgnoreCase(String name);

    List<CityEntity> findAllByCantonId(Long cantonId);

    List<CityEntity> findAllByCanton_Country_Id(Long countryId);

}
