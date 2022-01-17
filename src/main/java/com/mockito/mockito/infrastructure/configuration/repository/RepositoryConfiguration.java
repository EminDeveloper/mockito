package com.mockito.mockito.infrastructure.configuration.repository;

import com.mockito.mockito.domain.repository.CantonRepository;
import com.mockito.mockito.domain.repository.CityRepository;
import com.mockito.mockito.domain.repository.CountryRepository;
import com.mockito.mockito.infrastructure.database.repository.CantonEntityRepository;
import com.mockito.mockito.infrastructure.database.repository.CityEntityRepository;
import com.mockito.mockito.infrastructure.database.repository.CountryEntityRepository;
import com.mockito.mockito.infrastructure.mapper.CantonMapper;
import com.mockito.mockito.infrastructure.mapper.CityMapper;
import com.mockito.mockito.infrastructure.mapper.CountryMapper;
import com.mockito.mockito.infrastructure.repository.CantonRepositoryImpl;
import com.mockito.mockito.infrastructure.repository.CityRepositoryImpl;
import com.mockito.mockito.infrastructure.repository.CountryRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public CityRepository cityRepository(
            CityEntityRepository cityEntityRepository,
            CityMapper cityMapper) {
        return new CityRepositoryImpl(cityEntityRepository, cityMapper);
    }

    @Bean
    public CantonRepository cantonRepository(
            CantonEntityRepository cantonEntityRepository,
            CantonMapper cantonMapper) {
        return new CantonRepositoryImpl(cantonEntityRepository, cantonMapper);
    }

    @Bean
    public CountryRepository countryRepository(
            CountryEntityRepository countryEntityRepository,
            CountryMapper countryMapper) {
        return new CountryRepositoryImpl(countryEntityRepository, countryMapper);
    }

}