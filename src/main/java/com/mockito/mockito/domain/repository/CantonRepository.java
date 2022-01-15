package com.mockito.mockito.domain.repository;

import com.mockito.mockito.domain.entity.Canton;
import com.mockito.mockito.domain.entity.Country;

import java.util.Optional;
import java.util.Set;

public interface CantonRepository extends BaseDomainRepository<Canton> {

    Optional<Canton> findByAbbreviation(String abbreviation);

    Optional<Canton> findByName(String name);

    Set<Canton> findAllByCountry(Country country);

}