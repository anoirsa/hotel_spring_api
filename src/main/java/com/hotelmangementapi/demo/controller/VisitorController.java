package com.hotelmangementapi.demo.controller;

import com.hotelmangementapi.demo.model.dtos.requests.VisitorRequest;
import com.hotelmangementapi.demo.model.dtos.responses.ResponseResult;
import com.hotelmangementapi.demo.model.dtos.responses.VisitorResponse;
import com.hotelmangementapi.demo.service.VisitorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/visitors")
@AllArgsConstructor
public class VisitorController {
    private final VisitorService visitorService;

    @PostMapping
    public @ResponseBody
    ResponseResult addVisitor(@RequestBody VisitorRequest visitorRequest) {
        return visitorService.addVisitor(visitorRequest);
    }

    @GetMapping
    public @ResponseBody List<VisitorResponse> getAllVisitors() {
        return visitorService.getAllVisitors();
    }

}
