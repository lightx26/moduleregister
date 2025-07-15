package com.pet.moduleregister.adapters.out.persistence.shared.mapper;

import java.util.List;

public interface EntityMapper<D, E> {
    E toEntity(D domain);

    List<E> toEntity(List<D> domains);
}
