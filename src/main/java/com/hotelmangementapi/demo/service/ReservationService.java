package com.hotelmangementapi.demo.service;


import com.hotelmangementapi.demo.model.AppUser;
import com.hotelmangementapi.demo.model.Reservation;
import com.hotelmangementapi.demo.model.Room;
import com.hotelmangementapi.demo.model.Visitor;
import com.hotelmangementapi.demo.model.dtos.requests.ReservationRequest;
import com.hotelmangementapi.demo.model.dtos.requests.RoomRequestAndResponse;
import com.hotelmangementapi.demo.model.dtos.responses.ReservationResponse;
import com.hotelmangementapi.demo.model.enums.AppUserRole;
import com.hotelmangementapi.demo.model.enums.RoomType;
import com.hotelmangementapi.demo.repository.ReservationRepJpa;
import com.hotelmangementapi.demo.repository.RoomRepJpa;
import com.hotelmangementapi.demo.repository.VisitorRepJpa;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.hotelmangementapi.demo.model.enums.AppUserRole.*;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepJpa reservationRepJpa;
    private final RoomRepJpa roomRepJpa;
    private final VisitorRepJpa visitorRepJpa;
    // A method to check the availability of a room within a specific time
    public boolean checkAvailabilityOfRoom(LocalDate startDate , LocalDate endDate, String roomId){


        List<Reservation> allReservationsOfRoomId = reservationRepJpa.findReservationByReservedRoomId(roomId);

        boolean isRoomAvailable = !(allReservationsOfRoomId.stream().map(reservation -> {
            List<LocalDate> daysBetween = reservation.getStartingDate()
                    .datesUntil(reservation.getEndingDate()).collect(Collectors.toList());
            return daysBetween;
        }).anyMatch(reservationRange -> reservationRange.contains(startDate) || reservationRange.contains(endDate)));

        return isRoomAvailable;
    }

    public List<Room> getAvailableRooms(RoomType roomType, LocalDate startDate, LocalDate endDate) {

        List<Room> rooms = roomRepJpa.findRoomByRoomType(roomType);
        List<Room> availableRooms = rooms.stream().filter(room -> checkAvailabilityOfRoom(startDate, endDate, room.getRoomId()))
                .collect(Collectors.toList());
        return availableRooms;
    }

    // For normal booker
    public ReservationResponse reserveRoom(ReservationRequest reservationRequest) {

        LocalDate endDate = ProjectMappingServices.convertToLocalDate(reservationRequest.getEndingDate());
        LocalDate startDate = ProjectMappingServices.convertToLocalDate(reservationRequest.getStartingDate());

        List<Room> availableRooms = getAvailableRooms(reservationRequest.getRoomType(), startDate,endDate);
        if (availableRooms.size() == 0) throw new IllegalStateException("No available room");
        Room room;
        if (reservationRequest.getRequesterType().equals(BOOKER)) {
            Random randomChoice = new Random();
            room = availableRooms.get(randomChoice.nextInt(availableRooms.size()));
            reservationRequest.setReservedRoomId(room.getRoomId());
        } else {
            room = availableRooms.stream().filter(r -> r.getRoomId().equals(reservationRequest.getReservedRoomId()))
                    .findFirst().orElseThrow(() -> new IllegalStateException("No room with this ID is found"));
        }
        reservationRequest
                .setReservationSpecialId(ProjectServices
                        .generateReservationSpecialId(startDate,reservationRequest.getVisitorLastName(),room.getRoomId()));
        Reservation reservationToBeAdded = ProjectMappingServices.mapToReservation(reservationRequest);
        visitorRepJpa.findVisitorByFirstNameAndLastName(reservationRequest.getVisitorFirstName(), reservationRequest.getVisitorFirstName())
                .ifPresentOrElse((visitor) -> {
                    reservationToBeAdded.setVisitorWhoReserved(visitor);
                },() -> {
                    Visitor newVisitor = new Visitor(reservationRequest.getVisitorFirstName(),reservationRequest.getVisitorLastName(),
                            reservationRequest.getVisitorGender());
                    visitorRepJpa.save(newVisitor);
                    reservationToBeAdded.setVisitorWhoReserved(newVisitor);
                });
        reservationToBeAdded.setRoomToBeReserved(room);
        reservationRepJpa.save(reservationToBeAdded);
        ReservationResponse reservationResponse = ProjectMappingServices.mapToReservationResponse(reservationToBeAdded);
        return reservationResponse;
    }




}
