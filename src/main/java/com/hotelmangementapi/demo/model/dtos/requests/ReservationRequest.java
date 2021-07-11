package com.hotelmangementapi.demo.model.dtos.requests;

import com.hotelmangementapi.demo.model.AppUser;
import com.hotelmangementapi.demo.model.enums.AppUserRole;
import com.hotelmangementapi.demo.model.enums.Gender;
import com.hotelmangementapi.demo.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {


    private String reservationSpecialId;
    private String reservedRoomId;
    private String visitorLastName;
    private String visitorFirstName;
    private String startingDate;
    private String endingDate;
    private RoomType roomType;
    private Gender visitorGender;
    private AppUserRole requesterType;


    public ReservationRequest(String reservedRoomId,
                              String visitorLastName, String visitorFirstName, String startingDate, String endingDate,
                              Gender visitorGender) {
        this.reservationSpecialId = reservationSpecialId;
        this.reservedRoomId = reservedRoomId;
        this.visitorLastName = visitorLastName;
        this.visitorFirstName = visitorFirstName;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.visitorGender = visitorGender;
    }


    public ReservationRequest( String visitorLastName, String visitorFirstName, String startingDate,
                               String endingDate, RoomType roomType, Gender visitorGender) {
        this.reservationSpecialId = reservationSpecialId;
        this.visitorLastName = visitorLastName;
        this.visitorFirstName = visitorFirstName;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.roomType = roomType;
        this.visitorGender = visitorGender;
    }


}
