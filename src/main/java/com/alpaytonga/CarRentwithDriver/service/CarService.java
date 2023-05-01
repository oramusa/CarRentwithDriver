package com.alpaytonga.CarRentwithDriver.service;

import com.alpaytonga.CarRentwithDriver.entity.Car;

import com.alpaytonga.CarRentwithDriver.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;


    //posting data, user enter information coming from front end , going to back end , to mysql database
    public Car saveCars(Car car){
        return carRepository.save(car);
    }

    //Fetching data
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public void deleteDetails(long id) {
        carRepository.deleteById(id);
    }

    public Car getUserById(Long id) {
        Optional<Car> user = carRepository.findById(id);
        return user.orElse(null);
    }


    public Car saveDetails(Car car) {
        return carRepository.save(car);
    }
}




