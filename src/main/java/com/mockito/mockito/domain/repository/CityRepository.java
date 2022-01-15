package com.mockito.mockito.domain.repository;


import com.mockito.mockito.domain.entity.Canton;
import com.mockito.mockito.domain.entity.City;
import com.mockito.mockito.domain.entity.Country;

import java.util.Optional;
import java.util.Set;

public interface CityRepository extends BaseDomainRepository<City> {

    Optional<City> findByName(String name);

    Set<City> findAllByCanton(Canton canton);

    Set<City> findAllByCountry(Country country);

}
