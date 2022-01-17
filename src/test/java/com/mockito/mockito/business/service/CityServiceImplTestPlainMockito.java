package com.mockito.mockito.business.service;

import com.mockito.mockito.domain.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class CityServiceImplTestPlainMockito {

    @BeforeEach
    void setUp() {
        CityRepository cityRepository = Mockito.mock(CityRepository.class);
        CityService cityService = new CityServiceImpl(cityRepository);
    }

}