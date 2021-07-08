package com.hotelmangementapi.demo.service;


import com.hotelmangementapi.demo.model.Room;
import com.hotelmangementapi.demo.model.dtos.responses.ResponseResult;
import com.hotelmangementapi.demo.model.dtos.requests.RoomRequestAndResponse;
import com.hotelmangementapi.demo.repository.RoomRepJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepJpa roomRepJpa;

    public ResponseResult addRoom(RoomRequestAndResponse room){

        RoomRequestAndResponse roomDto = room;
        roomDto.setAvailability(false);
        Room roomTobeAdded = ProjectMappingServices.mapRoomRequestToR(roomDto);
        roomRepJpa.save(roomTobeAdded);
        boolean addedIsSuccessful = roomRepJpa.existsById(roomTobeAdded.getId());

        return  ProjectServices.responseResult(addedIsSuccessful,
                (RoomRequestAndResponse)roomDto,"Error of some type");

    }

    public List<RoomRequestAndResponse> getAllRooms() {
        return ProjectMappingServices.mapToResponseList(roomRepJpa.findAll());
    }



}
