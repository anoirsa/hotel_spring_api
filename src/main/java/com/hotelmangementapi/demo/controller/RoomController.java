package com.hotelmangementapi.demo.controller;


import com.hotelmangementapi.demo.model.dtos.responses.ResponseResult;
import com.hotelmangementapi.demo.model.dtos.requests.RoomRequestAndResponse;
import com.hotelmangementapi.demo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rooms")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add_room')")
    public ResponseResult addRoom(@RequestBody RoomRequestAndResponse roomRequestAndResponse){
       // roomService.addRoom(roomRequestAndResponse);
        return roomService.addRoom(roomRequestAndResponse);
    }
    @GetMapping
    public @ResponseBody
    List<RoomRequestAndResponse> findAll() {
        return roomService.getAllRooms();
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAnyAuthority('user:remove_room')")
    public @ResponseBody
    ResponseResult deleteRoomById(@PathVariable("id")String id) {
        return roomService.deleteRoomByRoomId(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('user:edit_room')")
    public @ResponseBody
    ResponseResult editRoomDetails(@RequestBody RoomRequestAndResponse roomRequestAndResponse,
                                   @PathVariable String id) {
        return roomService.editRoomDetails(roomRequestAndResponse,id);
    }





}
