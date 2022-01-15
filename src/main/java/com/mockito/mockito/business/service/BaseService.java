package com.mockito.mockito.business.service;


import java.util.function.Supplier;

public interface BaseService<D extends BaseDomain, E extends Exception> {

    D save(D domain) throws E;

    D find(Long id) throws E;

    void delete(D domain) throws E;

    Supplier<E> createSupplierOnElementNotFound(String message);

}
