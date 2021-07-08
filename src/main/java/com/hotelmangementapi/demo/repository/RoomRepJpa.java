package com.hotelmangementapi.demo.repository;

import com.hotelmangementapi.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoomRepJpa extends JpaRepository<Room, Long> {


    Optional<Room> findByRoomId(String roomSpecialId);


}
