package com.hotelmangementapi.demo.repository;

import com.hotelmangementapi.demo.model.Room;
import com.hotelmangementapi.demo.model.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



public interface RoomRepJpa extends JpaRepository<Room, Long> {


    Optional<Room> findByRoomId(String roomSpecialId);

    Optional<Room> deleteRoomByRoomId(String roomId);

    List<Room> findRoomByRoomType(RoomType roomType);


}
