package com.alpaytonga.CarRentwithDriver.controller;


import com.alpaytonga.CarRentwithDriver.entity.Ride;

import com.alpaytonga.CarRentwithDriver.repository.CarRepository;
import com.alpaytonga.CarRentwithDriver.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;


@RestController
@RequestMapping("/ride")
public class RideController {


    private final RideService rideService;
    private final CarRepository carRepository;

    @Autowired
    public RideController(RideService rideService, CarRepository carRepository) {
        this.rideService = rideService;
        this.carRepository = carRepository;
    }

    @PostMapping("/added")
    public ResponseEntity<Ride> postRides(@RequestBody Ride ride) {
        Ride savedRide = rideService.saveRides(ride);

        if (savedRide != null) {
            // Return a success response with the saved ride
            return ResponseEntity.ok(savedRide);
        } else {
            // Return an error response indicating that the ride could not be saved
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/getRide")
    public List<Ride> getRides() {
        return rideService.getAllRides();
    }


    @DeleteMapping("/delete")
    public String deleteRide(@RequestParam("ride_id") long ride_id) {
        rideService.deleteRide(ride_id);
        return "deleted successfully";
    }


    @PutMapping("/updateRide")
    public ResponseEntity<Ride> putDetails(@RequestParam("id") Long id, @RequestBody Ride updatedRide) {
        // Check if the user with the given ID exists
        Ride existingRide = rideService.getRideById(id);

        if (existingRide != null) {
            // Update the existing user with the new details
            existingRide.setCost(updatedRide.getCost());
            existingRide.setDuration(updatedRide.getDuration());
           existingRide.setEndAddress(updatedRide.getEndAddress());
           existingRide.setStartAddress(updatedRide.getStartAddress());
           existingRide.setRideStartTime(updatedRide.getRideStartTime());
           existingRide.setRideEndTime(updatedRide.getRideEndTime());
            // Update other user details as needed

            // Save the updated user
            Ride savedRide = rideService.saveRides(existingRide);

            // Return a success response with the updated user
            return ResponseEntity.ok(savedRide);
        } else {
            // Return an error response indicating that the user was not found
            return ResponseEntity.notFound().build();
        }




    }

}