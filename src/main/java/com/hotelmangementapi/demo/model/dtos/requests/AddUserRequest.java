package com.hotelmangementapi.demo.model.dtos.requests;

import com.hotelmangementapi.demo.model.enums.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter

@ToString

public class AddUserRequest {
    private  String username;
    private  String password;
    private  String email;
    private  AppUserRole appUserRole;
}
