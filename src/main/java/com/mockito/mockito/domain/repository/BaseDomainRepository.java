package com.mockito.mockito.domain.repository;


import com.mockito.mockito.domain.entity.BaseDomain;

import java.util.Optional;

public interface BaseDomainRepository<D extends BaseDomain> {

    Optional<D> save(D domain);

    Optional<D> find(Long id);

    void delete(D domain);

}