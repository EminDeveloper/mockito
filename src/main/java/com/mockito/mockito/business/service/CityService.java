package com.mockito.mockito.business.service;

import java.util.Set;


public interface CityService extends BaseService<City, ElementNotFoundException> {

    City findByName(String name) throws ElementNotFoundException;

    Set<City> findAllByCanton(Canton canton);

    Set<City> findAllByCountry(Country country);

}
