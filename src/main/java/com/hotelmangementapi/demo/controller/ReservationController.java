package com.hotelmangementapi.demo.controller;


// Reservation controller for normal user

import com.hotelmangementapi.demo.model.Reservation;
import com.hotelmangementapi.demo.model.dtos.requests.ReservationRequest;
import com.hotelmangementapi.demo.model.dtos.responses.ReservationResponse;
import com.hotelmangementapi.demo.service.ProjectMappingServices;
import com.hotelmangementapi.demo.service.ProjectServices;
import com.hotelmangementapi.demo.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/reservation/user")
@AllArgsConstructor

public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add_reservation')")
    public @ResponseBody
    ReservationResponse reserveRoom(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.reserveRoom(reservationRequest);
    }

    @PostMapping("/get")
    public @ResponseBody
    List<Reservation> getAvailableReservations(@RequestBody ReservationRequest reservationRequest) {
        LocalDate startDate = ProjectMappingServices.convertToLocalDate(reservationRequest.getStartingDate());
        LocalDate endDate = ProjectMappingServices.convertToLocalDate(reservationRequest.getEndingDate());
        return  reservationService.getAvailableReservations(reservationRequest.getRoomType(),
                startDate,endDate);
    }



}
