package com.pet.moduleregister.domain.student;

import com.pet.moduleregister.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Student extends User {
    private Long cohortId;
    private Long studentGroupId;
}
