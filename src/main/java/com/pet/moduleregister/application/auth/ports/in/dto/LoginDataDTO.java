package com.pet.moduleregister.application.auth.ports.in.dto;


public record LoginDataDTO(
    String userCode,
    String password
) {}
