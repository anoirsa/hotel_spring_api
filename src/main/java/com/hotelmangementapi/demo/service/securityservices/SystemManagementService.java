package com.hotelmangementapi.demo.service.securityservices;


import com.hotelmangementapi.demo.model.AppUser;
import com.hotelmangementapi.demo.model.dtos.requests.AddUserRequest;
import com.hotelmangementapi.demo.model.dtos.responses.ResponseResult;
import com.hotelmangementapi.demo.model.dtos.responses.UserResponse;
import com.hotelmangementapi.demo.repository.AppUserRep;
import com.hotelmangementapi.demo.service.ProjectMappingServices;
import com.hotelmangementapi.demo.service.ProjectServices;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
@AllArgsConstructor
public class SystemManagementService {
    private final AppUserRep appUserRep;
    private final PasswordEncoder passwordEncoder;


    public ResponseResult registerAppUser(AddUserRequest appUserRequest) {
        appUserRequest.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));
        // Validation of the already database of users
        boolean usernamePresent = appUserRep.findAppUserByUsername(appUserRequest.getUsername()).isPresent();
        boolean emailPresent = appUserRep.findAppUserByEmail(appUserRequest.getEmail()).isPresent();
        if (usernamePresent || emailPresent) throw new ValidationException("The username or email are already in our database");
        AppUser appUser = ProjectMappingServices.mapToAppUser(appUserRequest);
        appUserRep.save(appUser);
        boolean userAdditionSuccessful = appUserRep.existsById(appUser.getId());
        return ProjectServices.responseResult(userAdditionSuccessful,
                (UserResponse) ProjectMappingServices.mapToUserResponse(appUser),"User Addition problem");
    }

}
