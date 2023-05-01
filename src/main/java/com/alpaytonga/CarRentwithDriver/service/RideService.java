package com.alpaytonga.CarRentwithDriver.service;

import com.alpaytonga.CarRentwithDriver.entity.Car;
import com.alpaytonga.CarRentwithDriver.entity.Ride;
import com.alpaytonga.CarRentwithDriver.repository.CarRepository;
import com.alpaytonga.CarRentwithDriver.repository.RideRepository;

import com.alpaytonga.CarRentwithDriver.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class RideService {

    @Autowired
    public RideRepository rideRepository;

    @Autowired
    public CarRepository carRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public EntityManager entityManager;

   private List<Ride> getRidesByCar(Car car) {
        return rideRepository.findByCar(car);
    }


    //posting data, ride enter information coming from front end
    public Ride saveRides(Ride ride) {
        return rideRepository.save(ride);
    }


    //fetching ride data
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    //update ride data
    public Ride getRideById(Long id) {
        Optional<Ride> ride = rideRepository.findById(id);
        return ride.orElse(null);
    }

    //delete ride data

    public void deleteRide(long id) {
        rideRepository.deleteById(id);
    }


   // Check if a ride can be scheduled for the given car and start time
    public String isRideAvailable(Car car, LocalDateTime startTime, LocalDateTime endTime) {
        List<Ride> rides = getRidesByCar(car);

        // Check if the car is available during the requested time frame
        for (Ride ride : rides) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            CharSequence charSequence = now.format(formatter);




            LocalDateTime desiredStartTime = startTime;
            LocalDateTime desiredEndTime = endTime;

            ChronoLocalDateTime<?> rideEndTime = null;
            ChronoLocalDateTime<?> rideStartTime = null;
            if (desiredStartTime.isBefore(rideEndTime) && desiredEndTime.isAfter(rideStartTime)) {
                // the car is not available
                return "The car is not available at that time.";
            } else {
                // the car is available
                return "The car is available at that time.";
            }
        }

        // If no rides overlap with the requested time frame, the car is available
        return "The car is available at that time.";
    }


    public void cancelRide(Long id) {
        Optional<Ride> optionalRide = rideRepository.findById(id);
        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            // You can add any additional logic for ride cancellation here,
            // such as updating ride status, calculating cancellation fees, etc.

            // Delete the ride from the database
            rideRepository.deleteById(id);
        } else {
            // Handle case when ride with the given ID is not found
            throw new IllegalArgumentException("Ride not found with ID: " + id);
        }
    }








}








