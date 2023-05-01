package com.alpaytonga.CarRentwithDriver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

import java.util.HashSet;

import java.util.Set;

@Entity
@Table(name="ride")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ride {

    @Id
    @GeneratedValue
    @Column(name = "ride_id")
    private long rideId;

    @Column(name = "start_address")
    private String startAddress;

    @Column(name = "end_address")
    private String endAddress;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "ride_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime rideStartTime;

    @Column(name = "ride_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime rideEndTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_ride",
            joinColumns = {@JoinColumn(name = "ride_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rented_by_user_id")
    private User rentedBy;

    public void addUser(User user) {
        if (user != null) {
            users.add(user);
        } else {
            throw new IllegalArgumentException("User cannot be null.");
        }
    }

    public void removeUser(User user) {
        if (user != null) {
            users.remove(user);
        } else {
            throw new IllegalArgumentException("User cannot be null.");
        }
    }
    public Long getCarId() {
        boolean car;
        if (car != null) {
            return car.getCarId();
        } else {
            return null;
        }
    }

    public void setCar(Car car) {
        if (car != null) {
            this.car = car;
        } else {
            throw new IllegalArgumentException("Car cannot be null.");
        }
    }


    public void createRideWithCar() {
        Ride ride = new Ride();
        Car car = new Car();

        // Set car object to ride
        ride.setCar(car);

        // Check if ride.getCar() is not null before calling getCarId()
        if (ride.getCar() != null) {
            Long carId = ride.getCar().getCarId();
            // Now you can safely use carId as needed
        } else {
            // Handle the case when ride.getCar() is null
            System.err.println("Error: Car object is null for Ride");
        }
    }



    public void setUser(User user) {
    }


    public void setRentedBy(User user) {
        User rentedBy = null;
        if (user != null) {
            users.add(user);
            user.getRides().add(this);
        } else {
            users.remove(rentedBy);
            rentedBy.getRides().remove(this);
        }
        //this.rentedBy = user;
    }


}
