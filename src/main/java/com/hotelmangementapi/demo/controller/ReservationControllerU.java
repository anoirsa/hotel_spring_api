package com.hotelmangementapi.demo.controller;


// Reservation controller for normal user

import com.hotelmangementapi.demo.model.dtos.requests.ReservationRequest;
import com.hotelmangementapi.demo.model.dtos.responses.ReservationResponse;
import com.hotelmangementapi.demo.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reservation/user")
@AllArgsConstructor

public class ReservationControllerU {

    private final ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add_reservation')")
    public @ResponseBody
    ReservationResponse reserveRoom(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.reserveRoom(reservationRequest);
    }



}
