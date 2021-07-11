package com.hotelmangementapi.demo.repository;

import com.hotelmangementapi.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepJpa extends JpaRepository<Reservation, Long> {

    List<Reservation> findReservationByReservedRoomId(String roomId);

}
