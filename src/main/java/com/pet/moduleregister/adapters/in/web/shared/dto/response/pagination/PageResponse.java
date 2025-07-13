package com.pet.moduleregister.adapters.in.web.shared.dto.response.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public abstract class PageResponse<T> {
    private List<T> content;

    @JsonProperty("isFirst")
    private boolean isFirst;
    @JsonProperty("isLast")
    private boolean isLast;

    private boolean numberOfElements;
}
