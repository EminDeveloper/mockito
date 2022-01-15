package com.mockito.mockito.infrastructure.database.repository;

import com.mockito.mockito.infrastructure.database.entity.CantonEntity;

import java.util.List;
import java.util.Optional;

public interface CantonEntityRepository extends BaseEntityRepository<CantonEntity> {

    Optional<CantonEntity> findByNameIgnoreCase(String name);

    Optional<CantonEntity> findByAbbreviationIgnoreCase(String abbreviation);

    List<CantonEntity> findAllByCountryNameIgnoreCase(String countryName);

    List<CantonEntity> findAllByCountryId(Long countryId);

}
