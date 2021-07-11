package com.hotelmangementapi.demo;

import com.hotelmangementapi.demo.model.Room;
import com.hotelmangementapi.demo.model.enums.RoomType;
import com.hotelmangementapi.demo.repository.RoomRepJpa;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.hotelmangementapi.demo.model.enums.RoomType.*;

@SpringBootTest

class DemoApplicationTests {

	@Autowired
	private RoomRepJpa roomRepJpa;

	@Test
	void contextLoads() {
	}

	@Test
	public void tester() {
		Room room1 = new Room("21232", DOUBLE, 2, "Big dsdand good");
		Room room2 = new Room("Mother", DOUBLE, 2, "Big dsdand good");
		roomRepJpa.save(room1);
		roomRepJpa.save(room2);



		List<Room> roomsTypes = roomRepJpa.findRoomByRoomType(DOUBLE);
		System.out.println(roomsTypes.size());

	}

}
