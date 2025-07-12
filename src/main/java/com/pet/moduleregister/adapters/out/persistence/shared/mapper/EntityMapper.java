package com.pet.moduleregister.adapters.out.persistence.shared.mapper;

public interface EntityMapper<D, E> {
    E toEntity(D domain);

    Iterable<E> toEntity(Iterable<D> domains);
}
