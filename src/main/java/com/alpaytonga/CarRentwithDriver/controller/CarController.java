package com.alpaytonga.CarRentwithDriver.controller;

import com.alpaytonga.CarRentwithDriver.entity.Car;
import com.alpaytonga.CarRentwithDriver.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/getCar")
    public List<Car> getCars(){
        return carService.getAllCars();
    }

    @PostMapping("/addCar")
    public Car postCars(@RequestBody Car car){
        return carService.saveCars(car);
    }

    @DeleteMapping("/delete")
    public String deleteDetails(@RequestParam("car_id") long car_id) {
        carService.deleteDetails(car_id);
        return "deleted successfully";
    }


    @PutMapping("/updateCar")
    public ResponseEntity<Car > putDetails(@RequestParam("id") Long id, @RequestBody Car  updatedCar) {
        // Check if the user with the given ID exists
        Car existingUser = carService.getUserById(id);

        if (existingUser != null) {
            // Update the existing user with the new details

            existingUser.setName(updatedCar.getName());
            existingUser.setBrand(updatedCar.getBrand());
            existingUser.setModel(updatedCar.getModel());

            // Update other user details as needed

            // Save the updated user
            Car savedUser = carService.saveDetails(existingUser);

            // Return a success response with the updated user
            return ResponseEntity.ok(savedUser);
        } else {
            // Return an error response indicating that the user was not found
            return ResponseEntity.notFound().build();
        }

    }
}
