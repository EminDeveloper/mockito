package com.mockito.mockito.business.service;

import com.mockito.mockito.business.exception.ElementNotFoundException;
import com.mockito.mockito.domain.entity.Canton;
import com.mockito.mockito.domain.entity.City;
import com.mockito.mockito.domain.entity.Country;
import com.mockito.mockito.domain.repository.CityRepository;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class CityServiceImpl implements CityService {

    private static final String EXCEPTION_MESSAGE_TEMPLATE = "City with %s does not exist.";
    private final CityRepository cityRepository;
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City save(City city) throws ElementNotFoundException {
        String exceptionMessage = String.format(EXCEPTION_MESSAGE_TEMPLATE, "ID " + city.getId());
        return cityRepository.save(city)
                .orElseThrow(createSupplierOnElementNotFound(exceptionMessage));
    }

    @Override
    public City find(Long id) throws ElementNotFoundException {
        String exceptionMessage = String.format(EXCEPTION_MESSAGE_TEMPLATE, "ID " + id);
        return cityRepository.find(id)
                .orElseThrow(createSupplierOnElementNotFound(exceptionMessage));
    }

    @Override
    public void delete(City domain) {
        cityRepository.delete(domain);
    }

    @Override
    public Supplier<ElementNotFoundException> createSupplierOnElementNotFound(String message) {
        return () -> new ElementNotFoundException(message);
    }

    @Override
    public City findByName(String name) throws ElementNotFoundException {
        String exceptionMessage = String.format(EXCEPTION_MESSAGE_TEMPLATE, "name " + name);
        return cityRepository.findByName(name)
                .orElseThrow(createSupplierOnElementNotFound(exceptionMessage));
    }

    @Override
    public Set<City> findAllByCanton(Canton canton) {
        return cityRepository.findAllByCanton(canton).stream()
                .map(city -> City.builder()
                        .of(city)
                        .setCanton(canton)
                        .build())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<City> findAllByCountry(Country country) {
        return cityRepository.findAllByCountry(country);
    }

}