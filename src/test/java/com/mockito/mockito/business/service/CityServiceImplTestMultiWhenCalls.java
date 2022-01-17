package com.mockito.mockito.business.service;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.mockito.mockito.business.exception.ElementNotFoundException;
import com.mockito.mockito.domain.entity.Canton;
import com.mockito.mockito.domain.entity.City;
import com.mockito.mockito.domain.entity.Country;
import com.mockito.mockito.domain.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.unitils.reflectionassert.ReflectionAssert;

class CityServiceImplTestMultiWhenCalls {

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
    void failingTestWhenRelyingOnOrder() throws ElementNotFoundException {
        long id = 1;
        City one = createCity();
        City two = createCity();
        City three = createCity();
        Mockito.when(cityRepository.find(id)).thenReturn(Optional.of(one));
        Mockito.when(cityRepository.find(id)).thenReturn(Optional.of(two));
        Mockito.when(cityRepository.find(id)).thenReturn(Optional.of(three));
        ReflectionAssert.assertReflectionEquals(one, cityService.find(id));
        ReflectionAssert.assertReflectionEquals(two, cityService.find(id));
        ReflectionAssert.assertReflectionEquals(three, cityService.find(id));
    }

    @Test
    void successfulTestOnMultipleCalls() throws ElementNotFoundException {
        long id = 1;
        City one = createCity();
        City two = createCity();
        City three = createCity();
        Mockito.when(cityRepository.find(id)).thenReturn(Optional.of(one));
        Mockito.when(cityRepository.find(id)).thenReturn(Optional.of(two));
        Mockito.when(cityRepository.find(id)).thenReturn(Optional.of(three));
        ReflectionAssert.assertReflectionEquals(three, cityService.find(id));
        ReflectionAssert.assertReflectionEquals(three, cityService.find(id));
        ReflectionAssert.assertReflectionEquals(three, cityService.find(id));
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
