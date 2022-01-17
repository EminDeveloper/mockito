package com.mockito.mockito.examples;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


import com.mockito.mockito.business.exception.ElementNotFoundException;
import com.mockito.mockito.business.service.CityService;
import com.mockito.mockito.business.service.CityServiceImpl;
import com.mockito.mockito.domain.entity.Canton;
import com.mockito.mockito.domain.entity.City;
import com.mockito.mockito.domain.entity.Country;
import com.mockito.mockito.domain.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.unitils.reflectionassert.ReflectionAssert;

public class ExampleMockitoResetResolution {

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
    void find() throws ElementNotFoundException {
        City expected = createCity();
        Mockito.when(cityRepository.find(expected.getId())).thenReturn(Optional.of(expected));
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
    void findThrows() {
        City expected = createCity();
        Mockito.when(cityRepository.find(expected.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(ElementNotFoundException.class, () -> cityService.find(expected.getId()));
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