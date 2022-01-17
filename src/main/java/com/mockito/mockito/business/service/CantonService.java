package com.mockito.mockito.business.service;

import com.mockito.mockito.business.exception.ElementNotFoundException;
import com.mockito.mockito.domain.entity.Canton;
import com.mockito.mockito.domain.entity.Country;

import java.util.Set;

public interface CantonService extends BaseService<Canton, ElementNotFoundException> {

    Canton findByAbbreviation(String abbreviation, boolean loadCities) throws ElementNotFoundException;

    default Canton findByAbbreviation(String abbreviation) throws ElementNotFoundException {
        return findByAbbreviation(abbreviation, false);
    }

    Canton findByName(String name, boolean loadCities) throws ElementNotFoundException;

    default Canton findByName(String name) throws ElementNotFoundException {
        return findByName(name, false);
    }

    Set<Canton> findAllByCountry(Country country, boolean loadCities);

    default Set<Canton> findAllByCountry(Country country) {
        return findAllByCountry(country, false);
    }

}