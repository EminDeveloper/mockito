package com.mockito.mockito.business.service;

import com.mockito.mockito.business.exception.ElementNotFoundException;
import com.mockito.mockito.domain.entity.Canton;
import com.mockito.mockito.domain.entity.City;
import com.mockito.mockito.domain.entity.Country;
import java.util.Set;

public interface CityService extends BaseService<City, ElementNotFoundException> {

    City findByName(String name) throws ElementNotFoundException;

    Set<City> findAllByCanton(Canton canton);

    Set<City> findAllByCountry(Country country);

}
