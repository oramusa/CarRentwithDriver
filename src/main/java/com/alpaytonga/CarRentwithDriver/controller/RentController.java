package com.alpaytonga.CarRentwithDriver.controller;

import com.alpaytonga.CarRentwithDriver.entity.Car;
import com.alpaytonga.CarRentwithDriver.entity.Ride;
import com.alpaytonga.CarRentwithDriver.entity.User;
import com.alpaytonga.CarRentwithDriver.repository.CarRepository;
import com.alpaytonga.CarRentwithDriver.repository.RideRepository;
import com.alpaytonga.CarRentwithDriver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rent")
public class RentController {


    @Autowired
    private RideRepository rideRepository; // Inject the Ride repository

    @Autowired
    private CarRepository carRepository; // Inject the Car Repository

    @Autowired
    private UserRepository userRepository; // Inject the User Repository

    // Endpoint for renting a car
    @PostMapping("/rentals")
    @Transactional // Enable transaction management
    public ResponseEntity<?> rentCar(@RequestParam long userId, @RequestParam long carId) {
        try {
            // Validate request parameters
            if (userId <= 0 || carId <= 0) {
                return ResponseEntity.badRequest().body("Invalid user ID or car ID.");
            }

            // Fetch the user and car from the repositories using their IDs
            User user = userRepository.findById(userId).orElse(null);
            Car car = carRepository.findById(carId).orElse(null);

            if (user == null || car == null) {
                // If user or car not found, return an error response
                return ResponseEntity.badRequest().body("User or car not found.");
            }

            // Check if the car is already rented
            if (car.isRented()) {
                return ResponseEntity.badRequest().body("Car is already rented.");
            }

            // Create a new ride and associate it with the user and car
            Ride ride = new Ride();
            ride.setUser(user);
            ride.setCar(car);

            // Save the ride to the repository
            rideRepository.save(ride);

            // Update car status to rented
            car.setRented(true);
            carRepository.save(car);

            // Return a success response
            return ResponseEntity.ok("Car rented successfully.");
        } catch (Exception e) {
            // Handle exceptions and return an error response
            // You can log the error details or provide a more meaningful error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to rent car.");
        }
    }

    // Endpoint for canceling a rented car
    @PostMapping("/cancel")
    @Transactional // Enable transaction management
    public ResponseEntity<?> cancelCarRental(@RequestParam long userId, @RequestParam long carId) {
        try {
            // Validate request parameters
            if (userId <= 0 || carId <= 0) {
                return ResponseEntity.badRequest().body("Invalid user ID or car ID.");
            }

            // Fetch the user and car from the repositories using their IDs
            User user = userRepository.findById(userId).orElse(null);
            Car car = carRepository.findById(carId).orElse(null);

            if (user == null || car == null) {
                // If user or car not found, return an error response
                return ResponseEntity.badRequest().body("User or car not found.");
            }

            // Check if the car is rented by the user
            Ride ride = rideRepository.findByUserAndCar(user, car);
            if (ride == null) {
                return ResponseEntity.badRequest().body("Car is not rented by the user.");
            }

            // Delete the ride from the repository
            rideRepository.delete(ride);

            // Update car status to not rented
            car.setRented(false);
            carRepository.save(car);

            // Return a success response
            return ResponseEntity.ok("Car rental canceled successfully.");
        } catch (Exception e) {
            // Handle exceptions and return an error response
            // You can log the error details or provide a more meaningful error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel car rental.");
        }
    }

}