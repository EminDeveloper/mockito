package com.mockito.mockito.examples;

import com.mockito.mockito.business.service.CityService;
import com.mockito.mockito.business.service.CityServiceImpl;
import com.mockito.mockito.domain.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ExampleDontRecycleMocksResolution {

    private CityService cityService;

    @Mock
    private CityRepository cityRepository;

    void initializeScenarioOne() {
        // Mockito behaviour declarations
    }

    void initializeScenarioTwo() {
        // Mockito behaviour declarations
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cityService = new CityServiceImpl(cityRepository);
    }

    @Test
    void testOne() {
        initializeScenarioOne();
        // Test Case One
    }

    @Test
    void testTwo() {
        initializeScenarioTwo();
        // Test Case Two
    }

    @Test
    void testThree() {
        initializeScenarioOne();
        // ...
        initializeScenarioTwo();
        // ...
        // Another Test Case
    }
}