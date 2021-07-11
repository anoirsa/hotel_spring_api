package com.hotelmangementapi.demo.controller;


import com.hotelmangementapi.demo.model.dtos.requests.ReservationRequest;
import com.hotelmangementapi.demo.model.dtos.responses.ReservationResponse;
import com.hotelmangementapi.demo.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

// Reservation controller of manager or admin
@RestController
@RequestMapping("api/v1/reservation/admin_or_manager")
@AllArgsConstructor
public class ReservationControllerMA {

    private final ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add_reservation_specific')")
    public @ResponseBody ReservationResponse reserveRoom(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.reserveRoom(reservationRequest);
    }
}
