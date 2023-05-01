package com.alpaytonga.CarRentwithDriver.repository;

import com.alpaytonga.CarRentwithDriver.entity.Car;
import com.alpaytonga.CarRentwithDriver.entity.Ride;
import com.alpaytonga.CarRentwithDriver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import java.util.List;
import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByCar(Car car);


    Ride findByUserAndCar(User user, Car car);
}
