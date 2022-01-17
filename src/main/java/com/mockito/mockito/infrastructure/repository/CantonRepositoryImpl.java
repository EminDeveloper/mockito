package com.mockito.mockito.infrastructure.repository;

import com.mockito.mockito.domain.entity.Canton;
import com.mockito.mockito.domain.entity.Country;
import com.mockito.mockito.domain.repository.CantonRepository;
import com.mockito.mockito.infrastructure.database.entity.CantonEntity;
import com.mockito.mockito.infrastructure.database.repository.CantonEntityRepository;
import com.mockito.mockito.infrastructure.mapper.CantonMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CantonRepositoryImpl implements CantonRepository {

    private final CantonEntityRepository cantonEntityRepository;
    private final CantonMapper cantonMapper;

    public CantonRepositoryImpl(
            CantonEntityRepository cantonEntityRepository,
            CantonMapper cantonMapper) {
        this.cantonEntityRepository = cantonEntityRepository;
        this.cantonMapper = cantonMapper;
    }

    @Override
    public Optional<Canton> save(Canton domain) {
        CantonEntity cantonEntity = cantonMapper.toEntity(domain);
        cantonEntity = cantonEntityRepository.save(cantonEntity);
        return cantonMapper.toDomain(cantonEntity);
    }

    @Override
    public Optional<Canton> find(Long id) {
        Optional<CantonEntity> cantonEntity = cantonEntityRepository.findById(id);
        return cantonMapper.toDomain(cantonEntity);
    }

    @Override
    public void delete(Canton domain) {
        Optional<CantonEntity> cantonEntity = cantonEntityRepository.findById(domain.getId());
        cantonEntity.ifPresent(cantonEntityRepository::delete);
    }

    @Override
    public Optional<Canton> findByAbbreviation(String abbreviation) {
        Optional<CantonEntity> cantonEntity = cantonEntityRepository.findByAbbreviationIgnoreCase(abbreviation);
        return cantonMapper.toDomain(cantonEntity);
    }

    @Override
    public Optional<Canton> findByName(String name) {
        Optional<CantonEntity> cantonEntity = cantonEntityRepository.findByNameIgnoreCase(name);
        return cantonMapper.toDomain(cantonEntity);
    }

    @Override
    public Set<Canton> findAllByCountry(Country country) {
        List<CantonEntity> cantonEntities = cantonEntityRepository.findAllByCountryNameIgnoreCase(country.getName());
        return cantonMapper.toDomain(cantonEntities);
    }

}
