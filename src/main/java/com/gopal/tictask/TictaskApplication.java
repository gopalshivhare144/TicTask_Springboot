package com.gopal.tictask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TictaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TictaskApplication.class, args);
	}

}
