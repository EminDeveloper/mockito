package com.mockito.mockito.infrastructure.database.repository;

import com.mockito.mockito.infrastructure.database.entity.CountryEntity;

import java.util.Optional;

public interface CountryEntityRepository extends BaseEntityRepository<CountryEntity> {

    Optional<CountryEntity> findByNameIgnoreCase(String name);

    Optional<CountryEntity> findByCodeIgnoreCase(String code);

}