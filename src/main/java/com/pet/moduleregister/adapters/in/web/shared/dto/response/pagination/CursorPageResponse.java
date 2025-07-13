package com.pet.moduleregister.adapters.in.web.shared.dto.response.pagination;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class CursorPageResponse<T, C> extends PageResponse<T> {
    private C cursor;
    private int size;
    private C nextCursor;
}
