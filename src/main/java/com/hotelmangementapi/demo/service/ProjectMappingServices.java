package com.hotelmangementapi.demo.service;

import com.hotelmangementapi.demo.model.AppUser;
import com.hotelmangementapi.demo.model.Reservation;
import com.hotelmangementapi.demo.model.Room;
import com.hotelmangementapi.demo.model.Visitor;
import com.hotelmangementapi.demo.model.dtos.requests.AddUserRequest;
import com.hotelmangementapi.demo.model.dtos.requests.ReservationRequest;
import com.hotelmangementapi.demo.model.dtos.requests.RoomRequestAndResponse;
import com.hotelmangementapi.demo.model.dtos.requests.VisitorRequest;
import com.hotelmangementapi.demo.model.dtos.responses.ReservationResponse;
import com.hotelmangementapi.demo.model.dtos.responses.UserResponse;
import com.hotelmangementapi.demo.model.dtos.responses.VisitorResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProjectMappingServices {

    public static Room mapRoomRequestToR(RoomRequestAndResponse request) {
        return new Room(request.getRoomId(), request.getRoomType(),
                request.getFloorNum(),
                request.getDescription());
    }


    public static  RoomRequestAndResponse mapToResponse(Room room) {
        return new RoomRequestAndResponse(room.getRoomId(), room.getRoomType(), room.getFloorNum(),
                 room.getDescription(),room.getOriginalPrice());
    }

    public static List<RoomRequestAndResponse> mapToResponseList(List<Room> rooms) {
        List<RoomRequestAndResponse> listToBeReturned = new ArrayList<>();
        rooms.forEach((room) -> {
            listToBeReturned.add(mapToResponse(room));
        });
        return  listToBeReturned;
    }

    public static LocalDate convertToLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return  LocalDate.parse(value,formatter);
    }

    public static Visitor mapToVisitor(VisitorRequest visitorRequest) {
        return new Visitor(visitorRequest.getFirstName(),visitorRequest.getLastName(),
                visitorRequest.getGender());
    }

    public static VisitorResponse mapToVisitorResponse(Visitor visitor) {
        return new VisitorResponse(visitor.getLastName(),visitor.getGender());
    }

    public static List<VisitorResponse> mapToListOfVisitor(List<Visitor> visitors) {
        List<VisitorResponse> arrayToBeReturned = new ArrayList<>();
        visitors.forEach(visitor -> {
            arrayToBeReturned.add(new VisitorResponse(visitor.getLastName(), visitor.getGender()));
        });
        return arrayToBeReturned;
    }

    public static AppUser mapToAppUser(AddUserRequest appUserRequest) {
        return new AppUser(appUserRequest.getUsername(), appUserRequest.getPassword(),
                appUserRequest.getEmail(), appUserRequest.getAppUserRole());
    }

    public static UserResponse mapToUserResponse(AppUser appUser) {
        return new UserResponse(appUser.getUsername(),appUser.getEmail(),appUser.getAppUserRole());
    }


    public static Reservation mapToReservation(ReservationRequest reservationRequest) {
        return new Reservation(reservationRequest.getReservationSpecialId(),
                reservationRequest.getReservedRoomId(),reservationRequest.getVisitorLastName(),reservationRequest.getVisitorFirstName()
                ,convertToLocalDate(reservationRequest.getStartingDate()),convertToLocalDate(reservationRequest.getEndingDate()),
                reservationRequest.getRoomType()
              );
    }

    public static ReservationResponse mapToReservationResponse(Reservation reservation) {
        return new ReservationResponse(reservation.getReservationSpecialId(),reservation.getReservedRoomId()
        ,reservation.getVisitorLastName(),reservation.getStartingDate(),reservation.getEndingDate()
                ,reservation.getReservationPrice());
    }
}
