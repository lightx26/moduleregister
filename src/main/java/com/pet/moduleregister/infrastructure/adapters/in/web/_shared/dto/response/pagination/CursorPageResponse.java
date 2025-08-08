package com.pet.moduleregister.infrastructure.adapters.in.web._shared.dto.response.pagination;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CursorPageResponse<T, C> extends PageResponse<T> {
    private C cursor;
    private int size;
    private C nextCursor;
}
