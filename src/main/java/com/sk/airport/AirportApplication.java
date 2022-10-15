package com.sk.airport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.sk.airport.controller.AirportController;

@SpringBootApplication

public class AirportApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportApplication.class, args);

	}

}
