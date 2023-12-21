package com.example.hollidayCottages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.example.hollidayCottages")
public class HolidayCottagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayCottagesApplication.class, args);
	}

}
