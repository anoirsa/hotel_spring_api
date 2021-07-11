package com.hotelmangementapi.demo;

import com.hotelmangementapi.demo.model.AppUser;
import com.hotelmangementapi.demo.model.Room;
import com.hotelmangementapi.demo.model.enums.AppUserRole;
import com.hotelmangementapi.demo.model.enums.RoomType;
import com.hotelmangementapi.demo.repository.AppUserRep;
import com.hotelmangementapi.demo.repository.RoomRepJpa;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.hotelmangementapi.demo.model.enums.AppUserRole.*;

@SpringBootApplication

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AppUserRep appUserRep, RoomRepJpa roomRepJpa, PasswordEncoder passwordEncoder) {
		return args -> {
			appUserRep.save(new AppUser("admin",passwordEncoder.encode("15111996"),"Anouarbelilawoo@outlook",
					ADMIN));
			appUserRep.save(new AppUser("candi",passwordEncoder.encode("1234556"),"GOG@gmail.com",MANAGER));

			roomRepJpa.save(new Room("ST-001", RoomType.DOUBLE,2,"Big but small"));
			roomRepJpa.save(new Room("ST-002", RoomType.DOUBLE,2,"Small but big"));
			roomRepJpa.save(new Room("ST-003", RoomType.SINGLE,1,"Small but big"));
			roomRepJpa.save(new Room("ST-004", RoomType.SINGLE,3,"Small but big"));



		};
	}

}
