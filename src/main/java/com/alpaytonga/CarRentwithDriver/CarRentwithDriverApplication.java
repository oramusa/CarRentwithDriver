package com.alpaytonga.CarRentwithDriver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.alpaytonga.CarRentwithDriver")
public class CarRentwithDriverApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentwithDriverApplication.class, args);
	}

}
