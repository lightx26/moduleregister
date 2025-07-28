package com.pet.moduleregister.entities.student;

import com.pet.moduleregister.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Student extends User {
    private Long cohortId;
    private Long studentGroupId;
}
