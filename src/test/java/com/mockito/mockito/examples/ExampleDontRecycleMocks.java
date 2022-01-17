package com.mockito.mockito.examples;

import com.mockito.mockito.business.service.CityService;
import com.mockito.mockito.business.service.CityServiceImpl;
import com.mockito.mockito.domain.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ExampleDontRecycleMocks {

    private CityService cityService;

    @Mock
    private CityRepository cityRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cityService = new CityServiceImpl(cityRepository);
        // Mockito declarations for testOne
        // ...
        // Mockito declarations for testTwo
        // ...
    }

    @Test
    void testOne() {
        // Test Case One
    }

    @Test
    void testTwo() {
        // Test Case Two
    }

    @Test
    void test() {
        // Another Test Case
    }
}