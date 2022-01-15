package com.mockito.mockito.domain.repository;


import com.mockito.mockito.domain.entity.Country;

import java.util.Optional;

public interface CountryRepository extends BaseDomainRepository<Country> {

    Optional<Country> findByName(String name);

    Optional<Country> findByCode(String code);

}