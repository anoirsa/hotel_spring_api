package com.hotelmangementapi.demo.model.dtos.responses;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ReservationResponse {
    private String reservationSpecialId;
    private String reservedRoomId;
    private String visitorLastName;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private Double reservationPrice;


}
