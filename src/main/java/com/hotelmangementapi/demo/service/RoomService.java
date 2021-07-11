package com.hotelmangementapi.demo.service;


import com.hotelmangementapi.demo.model.Room;
import com.hotelmangementapi.demo.model.dtos.responses.ResponseResult;
import com.hotelmangementapi.demo.model.dtos.requests.RoomRequestAndResponse;
import com.hotelmangementapi.demo.repository.RoomRepJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class RoomService {


    private final RoomRepJpa roomRepJpa;

    public ResponseResult addRoom(RoomRequestAndResponse room){

        RoomRequestAndResponse roomDto = room;
        Room roomTobeAdded = ProjectMappingServices.mapRoomRequestToR(roomDto);
        roomRepJpa.save(roomTobeAdded);
        boolean addedIsSuccessful = roomRepJpa.existsById(roomTobeAdded.getId());

        return  ProjectServices.responseResult(addedIsSuccessful,
                (RoomRequestAndResponse)roomDto,"Error of some type");



    }

    public ResponseResult deleteRoomByRoomId(String roomId) {
        AtomicReference<ResponseResult> responseResult = null;
        roomRepJpa.deleteRoomByRoomId(roomId).ifPresentOrElse((room)->
        { responseResult.set(new ResponseResult(true, null, room));
        },() -> {
        responseResult.set(new ResponseResult(false,List.of("No such Id exsits"),null));
        });
        return responseResult.get();
    }

    public ResponseResult editRoomDetails(RoomRequestAndResponse roomRequestAndResponse, String roomId) {
        Room room = roomRepJpa.findByRoomId(roomId).orElse(null);
        if (room.equals(null)){
            return ProjectServices.responseResult(false,null,"No such as ID");
        }
        // Layers of modification
        room.setRoomId(roomRequestAndResponse.getRoomId() == null ?
                room.getRoomId() : roomRequestAndResponse.getRoomId());
        room.setRoomType(roomRequestAndResponse.getRoomType() == null ?
                room.getRoomType() : roomRequestAndResponse.getRoomType());
        room.setDescription(roomRequestAndResponse.getDescription() == null ?
                room.getDescription() : roomRequestAndResponse.getDescription());
        room.setFloorNum(roomRequestAndResponse.getFloorNum() == null ?
                room.getFloorNum() : roomRequestAndResponse.getFloorNum());

        roomRepJpa.save(room);
        return ProjectServices.responseResult(true,roomRequestAndResponse);
    }

    public List<RoomRequestAndResponse> getAllRooms() {
        return ProjectMappingServices.mapToResponseList(roomRepJpa.findAll());
    }





}
