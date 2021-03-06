package com.hotelmangementapi.demo.model.dtos.requests;

import com.hotelmangementapi.demo.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

public class RoomRequestAndResponse {

    private String roomId;
    private RoomType roomType;
    private  Integer floorNum;
    private  String description;
    private Double originalPrice;


}
