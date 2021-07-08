package com.hotelmangementapi.demo;

import com.hotelmangementapi.demo.model.AppUser;
import com.hotelmangementapi.demo.model.enums.AppUserRole;
import com.hotelmangementapi.demo.repository.AppUserRep;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.hotelmangementapi.demo.model.enums.AppUserRole.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AppUserRep appUserRep, PasswordEncoder passwordEncoder) {
		return args -> {
			appUserRep.save(new AppUser("admin",passwordEncoder.encode("15111996"),"Anouarbelilawoo@outlook",
					ADMIN));
			appUserRep.save(new AppUser("candi",passwordEncoder.encode("1234556"),"GOG@gmail.com",MANAGER));



		};
	}

}
