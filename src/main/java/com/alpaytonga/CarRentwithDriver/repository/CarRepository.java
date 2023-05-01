package com.alpaytonga.CarRentwithDriver.repository;

import com.alpaytonga.CarRentwithDriver.entity.Car;
import com.alpaytonga.CarRentwithDriver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    // Custom query methods
    List<Car> findByBrand(String brand);
    List<Car> findByUser(User user);
}
