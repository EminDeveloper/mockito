package com.mockito.mockito.infrastructure.configuration.business;

import com.mockito.mockito.business.service.*;
import com.mockito.mockito.domain.repository.CantonRepository;
import com.mockito.mockito.domain.repository.CityRepository;
import com.mockito.mockito.domain.repository.CountryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public CityService cityService(CityRepository cityRepository) {
        return new CityServiceImpl(cityRepository);
    }

    @Bean
    public CantonService cantonService(
            CantonRepository cantonRepository,
            CityService cityService) {
        return new CantonServiceImpl(cantonRepository, cityService);
    }

    @Bean
    public CountryService countryService(
            CountryRepository countryRepository,
            CantonService cantonService) {
        return new CountryServiceImpl(countryRepository, cantonService);
    }

}