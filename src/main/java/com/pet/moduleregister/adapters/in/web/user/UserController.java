package com.pet.moduleregister.adapters.in.web.user;

import com.pet.moduleregister.adapters.in.web.user.dto.response.CurrentUserResponse;
import com.pet.moduleregister.adapters.in.web.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        CurrentUserResponse userResponse = userFacade.getCurrentUser();
        return ResponseEntity.ok().body(userResponse);
    }
}
