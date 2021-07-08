package com.hotelmangementapi.demo.controller;


import com.hotelmangementapi.demo.model.dtos.requests.AddUserRequest;
import com.hotelmangementapi.demo.model.dtos.responses.ResponseResult;
import com.hotelmangementapi.demo.service.securityservices.SystemManagementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/user/management")
@AllArgsConstructor
public class SystemManagementController {

    private final SystemManagementService systemManagementService;

    @PostMapping
    public @ResponseBody
    ResponseResult registerNewUser(@RequestBody AddUserRequest addUserRequest) {
        return systemManagementService.registerAppUser(addUserRequest);
    }

}
