package com.mockito.mockito.infrastructure.repository;

import com.mockito.mockito.domain.entity.Canton;
import com.mockito.mockito.domain.entity.City;
import com.mockito.mockito.domain.entity.Country;
import com.mockito.mockito.domain.repository.CityRepository;
import com.mockito.mockito.infrastructure.database.entity.CityEntity;
import com.mockito.mockito.infrastructure.database.repository.CityEntityRepository;
import com.mockito.mockito.infrastructure.mapper.CityMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public class CityRepositoryImpl implements CityRepository {

    private final CityEntityRepository cityEntityRepository;
    private final CityMapper cityMapper;

    public CityRepositoryImpl(
            CityEntityRepository cityEntityRepository,
            CityMapper cityMapper) {
        this.cityEntityRepository = cityEntityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    public Optional<City> save(City domain) {
        CityEntity cityEntity = cityMapper.toEntity(domain);
        cityEntity = cityEntityRepository.save(cityEntity);
        return cityMapper.toDomain(cityEntity);
    }

    @Override
    public Optional<City> find(Long id) {
        Optional<CityEntity> cityEntity = cityEntityRepository.findById(id);
        return cityMapper.toDomain(cityEntity);
    }

    @Override
    public void delete(City domain) {
        Optional<CityEntity> cantonEntity = cityEntityRepository.findById(domain.getId());
        cantonEntity.ifPresent(cityEntityRepository::delete);
    }

    @Override
    public Optional<City> findByName(String name) {
        Optional<CityEntity> cityEntity = cityEntityRepository.findByNameIgnoreCase(name);
        return cityMapper.toDomain(cityEntity);
    }

    @Override
    public Set<City> findAllByCanton(Canton canton) {
        List<CityEntity> cityEntities = cityEntityRepository.findAllByCantonId(canton.getId());
        return cityMapper.toDomain(cityEntities);
    }

    @Override
    public Set<City> findAllByCountry(Country country) {
        List<CityEntity> cities = cityEntityRepository.findAllByCanton_Country_Id(country.getId());
        return cityMapper.toDomain(cities);
    }

}