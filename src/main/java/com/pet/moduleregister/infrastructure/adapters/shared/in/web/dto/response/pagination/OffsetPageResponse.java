package com.pet.moduleregister.infrastructure.adapters.shared.in.web.dto.response.pagination;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class OffsetPageResponse<T> extends PageResponse<T> {
    private int page;
    private int size;
    private Integer nextPage;
}
