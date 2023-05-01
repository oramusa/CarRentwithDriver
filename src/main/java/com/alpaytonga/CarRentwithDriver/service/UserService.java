package com.alpaytonga.CarRentwithDriver.service;

import com.alpaytonga.CarRentwithDriver.entity.User;
import com.alpaytonga.CarRentwithDriver.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //posting data, user enter information coming from front end , going to back end , to mysql database
    public User saveDetails(User user) {
        return userRepository.save(user);
    }

    // Retrieve user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //Fetching data, Retrieve all users from the database
    public List<User> getAllDetails() {
        return userRepository.findAll();
    }

    // Delete user by ID
    public void deleteDetails(long id) {
        userRepository.deleteById(id);
    }

    //update data
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

}











