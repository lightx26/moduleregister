package com.pet.moduleregister.application.moduleClass.ports.in.usecases;

import com.pet.moduleregister.application._shared.AuthUser;
import com.pet.moduleregister.application.moduleClass.dto.usecases.PersonalOpeningClass;

import java.util.List;

public interface GetMyOpeningModuleClasses {
    List<PersonalOpeningClass> getMyOpeningModuleClasses(AuthUser currentUser);
}
