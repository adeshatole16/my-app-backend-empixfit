package com.sport.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SportPlatformBackedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportPlatformBackedApplication.class, args);
	}

}
