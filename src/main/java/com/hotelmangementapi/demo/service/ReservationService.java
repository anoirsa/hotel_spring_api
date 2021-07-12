package com.hotelmangementapi.demo.service;


import com.google.common.util.concurrent.AtomicDouble;
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
import java.util.ArrayList;
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

    public List<Reservation> getAvailableReservations(RoomType roomType, LocalDate startDate, LocalDate endDate) {

        List<Room> rooms = roomRepJpa.findRoomByRoomType(roomType);
        List<Room> availableRooms = rooms.stream().filter(room -> checkAvailabilityOfRoom(startDate, endDate, room.getRoomId()))
                .collect(Collectors.toList());
        List<Reservation> availableReservations = availableRooms.stream().map(room -> new Reservation(null, room.getRoomId(), null, null, startDate, endDate, roomType))
                .collect(Collectors.toList());
        List<Double> prices = countPriceForReservationsAvailable(availableRooms, startDate, endDate);
        for (int i = 0 ; i < rooms.size() ; i++) {
            availableReservations.get(i).setRoomToBeReserved(availableRooms.get(i));
            availableReservations.get(i).setReservationPrice(prices.get(i));

        }
        return availableReservations;
    }

    // For normal booker
    public ReservationResponse reserveRoom(ReservationRequest reservationRequest) {

        LocalDate endDate = ProjectMappingServices.convertToLocalDate(reservationRequest.getEndingDate());
        LocalDate startDate = ProjectMappingServices.convertToLocalDate(reservationRequest.getStartingDate());
        List<Reservation> availableReservations = getAvailableReservations(reservationRequest.getRoomType(), startDate,endDate);
        if (availableReservations.size() == 0) throw new IllegalStateException("No available reservation");
        Reservation reservationToBeAdded = availableReservations.stream().filter(r -> r.getReservedRoomId().equals(reservationRequest.getReservedRoomId()))
                    .findFirst().orElseThrow(() -> new IllegalStateException("No reservation with this room ID is found"));
        reservationToBeAdded.setReservationSpecialId(ProjectServices
                .generateReservationSpecialId(startDate,reservationRequest.getVisitorLastName(),reservationToBeAdded.getReservedRoomId()));

        visitorRepJpa.findVisitorByFirstNameAndLastName(reservationRequest.getVisitorFirstName(), reservationRequest.getVisitorFirstName())
                .ifPresentOrElse((visitor) -> {
                    reservationToBeAdded.setVisitorWhoReserved(visitor);
                },() -> {
                    Visitor newVisitor = new Visitor(reservationRequest.getVisitorFirstName(),reservationRequest.getVisitorLastName(),
                            reservationRequest.getVisitorGender());
                    visitorRepJpa.save(newVisitor);
                    reservationToBeAdded.setVisitorWhoReserved(newVisitor);
                });
        reservationRepJpa.save(reservationToBeAdded);
        ReservationResponse reservationResponse = ProjectMappingServices.mapToReservationResponse(reservationToBeAdded);
        return reservationResponse;
    }

    public List<Double> countPriceForReservationsAvailable(List<Room> rooms,LocalDate startDate , LocalDate endDate) {
        List<Double> prices = new ArrayList<>();
        Double availabilityPrice = 40.00;
        for (int i = 0 ; i< rooms.size() ; i++)
        {
            availabilityPrice = 40.00 - 7.50;
            if (availabilityPrice <= 10.00) break;
        }
        /////////////
        Integer numberOfDaysFromNow= startDate.getDayOfMonth() - LocalDate.now().getDayOfMonth();
        Integer numberOfDays = endDate.getDayOfMonth() - startDate.getDayOfMonth();
        Double fromNowPrice = 50.00;
        for (int i = 0 ; i < numberOfDaysFromNow ; i++) {
            fromNowPrice = 50.00 - 5.00;
            if (fromNowPrice  == 0) break;
        }
        for (int i = 0 ; i < rooms.size() ; i++) {
            Double priceCount = (rooms.get(i).getOriginalPrice() * numberOfDays) + numberOfDaysFromNow + availabilityPrice;
            prices.add(priceCount);
        }
        return prices;
    }




}
