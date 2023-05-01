package com.alpaytonga.CarRentwithDriver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long userId;


    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name="user")
    private String user;


    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String pwd;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "address")
    private String address;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Car> cars;


    // Existing fields and annotations

    public User(String name) {
        this.name = name;
    }


    @ManyToMany(mappedBy = "users")
    private Set<Ride> rides = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_car",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> car = new ArrayList<>();

    @OneToMany(mappedBy = "rentedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ride> rentedRides = new ArrayList<>();

    public void addRide(Ride ride) {
        rides.add(ride);
        ride.setRentedBy(this);
    }


    public void removeRide(Ride ride) {
        rides.remove(ride);
        ride.setRentedBy(null);
    }



}
