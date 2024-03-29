package com.mockito.mockito.business.service;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.mockito.mockito.business.exception.ElementNotFoundException;
import com.mockito.mockito.domain.entity.Canton;
import com.mockito.mockito.domain.entity.City;
import com.mockito.mockito.domain.entity.Country;
import com.mockito.mockito.domain.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.unitils.reflectionassert.ReflectionAssert;


class CityServiceImplTest {

    // System under Test (SuT)
    private CityService cityService;

    // Mocks
    private CityRepository cityRepository;

    @BeforeEach
    void setUp() {
        cityRepository = Mockito.mock(CityRepository.class);
        cityService = new CityServiceImpl(cityRepository);
    }

    @Test
    void save() throws ElementNotFoundException {
        City expected = createCity();
        Mockito.when(cityRepository.save(expected))
                .thenReturn(Optional.of(expected));
        City actual = cityService.save(expected);
        ReflectionAssert.assertReflectionEquals(expected, actual);
    }

    @Test
    void find() throws ElementNotFoundException {
        City expected = createCity();
        Mockito.when(cityRepository.find(expected.getId()))
                .thenReturn(Optional.of(expected));
        City actual = cityService.find(expected.getId());
        ReflectionAssert.assertReflectionEquals(expected, actual);
    }

    @Test
    void delete() throws ElementNotFoundException {
        City expected = createCity();
        cityService.delete(expected);
        Mockito.verify(cityRepository).delete(expected);
    }

    @Test
    void findByName() throws ElementNotFoundException {
        City expected = createCity();
        Mockito.when(cityRepository.findByName(expected.getName()))
                .thenReturn(Optional.of(expected));
        City actual = cityService.findByName(expected.getName());
        ReflectionAssert.assertReflectionEquals(expected, actual);
    }

    @Test
    void findByNameThrowsExceptionIfCityNameContainsIllegalCharacter() {
        String cityName = "C!tyN@me";
        Mockito.when(cityRepository.findByName(cityName))
                .thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> cityService.findByName(cityName));
    }

    @Test
    void findAllByCanton() {
        City city = createCity();
        Canton canton = city.getCanton();
        Mockito.when(cityRepository.findAllByCanton(canton))
                .thenReturn(Collections.singleton(city));
        Set<City> expected = Set.of(city);
        Set<City> actual = cityService.findAllByCanton(canton);
        ReflectionAssert.assertReflectionEquals(expected, actual);
    }

    @Test
    void findAllByCountry() {
        City city = createCity();
        Country country = city.getCanton().getCountry();
        Mockito.when(cityRepository.findAllByCountry(country))
                .thenReturn(Collections.singleton(city));
        Set<City> expected = Set.of(city);
        Set<City> actual = cityService.findAllByCountry(country);
        ReflectionAssert.assertReflectionEquals(expected, actual);
    }

    private City createCity() {
        return City.builder()
                .id(System.currentTimeMillis())
                .version(ThreadLocalRandom.current().nextInt())
                .created(ZonedDateTime.now().minusDays(1L))
                .updated(ZonedDateTime.now())
                .setName("Swiss Test City " + System.currentTimeMillis())
                .setCanton(createCanton())
                .build();
    }

    private Canton createCanton() {
        return Canton.builder()
                .id(System.currentTimeMillis())
                .version(ThreadLocalRandom.current().nextInt())
                .created(ZonedDateTime.now().minusDays(1L))
                .updated(ZonedDateTime.now())
                .setName("Swiss Canton" + System.currentTimeMillis())
                .setAbbreviation("SC-" + System.currentTimeMillis())
                .setCountry(createCountry())
                .build();
    }

    private Country createCountry() {
        return Country.builder()
                .id(System.currentTimeMillis())
                .version(ThreadLocalRandom.current().nextInt())
                .created(ZonedDateTime.now().minusDays(1L))
                .updated(ZonedDateTime.now())
                .setName("Switzerland")
                .build();
    }

}
