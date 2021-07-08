package com.hotelmangementapi.demo.model.dtos.responses;

import com.hotelmangementapi.demo.model.enums.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter

public class UserResponse {
    private final String username;
    private final String email;
    private final AppUserRole appUserRole;
}
