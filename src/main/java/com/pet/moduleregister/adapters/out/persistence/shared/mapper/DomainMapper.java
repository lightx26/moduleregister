package com.pet.moduleregister.adapters.out.persistence.shared.mapper;

import java.util.List;

public interface DomainMapper<D, E> {
    D toDomain(E entity);

    List<D> toDomain(List<E> entities);
}
