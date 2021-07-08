package com.hotelmangementapi.demo.model.dtos.responses;

import com.hotelmangementapi.demo.model.dtos.requests.RoomRequestAndResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseResult {
    private boolean success;
    private List<String> errors;
    private Object responseObject;


}
