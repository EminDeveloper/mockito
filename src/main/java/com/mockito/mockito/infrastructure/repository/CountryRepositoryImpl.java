package com.mockito.mockito.infrastructure.repository;

import com.mockito.mockito.domain.entity.Country;
import com.mockito.mockito.domain.repository.CountryRepository;
import com.mockito.mockito.infrastructure.database.entity.CountryEntity;
import com.mockito.mockito.infrastructure.database.repository.CountryEntityRepository;
import com.mockito.mockito.infrastructure.mapper.CountryMapper;

import java.util.Optional;


public class CountryRepositoryImpl implements CountryRepository {

    private final CountryEntityRepository countryEntityRepository;
    private final CountryMapper countryMapper;

    public CountryRepositoryImpl(
            CountryEntityRepository countryEntityRepository,
            CountryMapper countryMapper) {
        this.countryEntityRepository = countryEntityRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public Optional<Country> save(Country domain) {
        CountryEntity countryEntity = countryMapper.toEntity(domain);
        countryEntity = countryEntityRepository.save(countryEntity);
        return countryMapper.toDomain(countryEntity);
    }

    @Override
    public Optional<Country> find(Long id) {
        Optional<CountryEntity> countryEntity = countryEntityRepository.findById(id);
        return countryMapper.toDomain(countryEntity);
    }

    @Override
    public void delete(Country domain) {
        Optional<CountryEntity> countryEntity = countryEntityRepository.findById(domain.getId());
        countryEntity.ifPresent(countryEntityRepository::delete);
    }

    @Override
    public Optional<Country> findByName(String name) {
        Optional<CountryEntity> countryEntity = countryEntityRepository.findByNameIgnoreCase(name);
        return countryMapper.toDomain(countryEntity);
    }

    @Override
    public Optional<Country> findByCode(String code) {
        Optional<CountryEntity> countryEntity = countryEntityRepository.findByCodeIgnoreCase(code);
        return countryMapper.toDomain(countryEntity);
    }

}
