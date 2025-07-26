package com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.dto.response;

import com.pet.moduleregister.infrastructure.adapters.moduleClass.in.web.dto.response.openingClass.OpeningClass;
import com.pet.moduleregister.infrastructure.adapters.shared.in.web.dto.response.pagination.CursorPageResponse;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class OpeningClassesResponse extends CursorPageResponse<OpeningClass, Long> {

}
