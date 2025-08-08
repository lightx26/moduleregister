package com.pet.moduleregister.infrastructure.adapters.in.web._shared.dto.response.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class PageResponse<T> {
    private List<T> content;

    @JsonProperty("isFirst")
    private boolean isFirst;
    @JsonProperty("isLast")
    private boolean isLast;

    private int numberOfElements;
}
