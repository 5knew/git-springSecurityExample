package com.example.workingAtSecurity.demo;

import com.example.workingAtSecurity.demo.model.User;
import com.example.workingAtSecurity.demo.model.enums.Role;
import com.example.workingAtSecurity.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public void run(String...args){
		User adminAccount = userRepository.findByRole(Role.ROLE_ADMIN);
		if(null == adminAccount){
			User user = new User();


			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setRole(Role.ROLE_ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);

		}
	}

}
