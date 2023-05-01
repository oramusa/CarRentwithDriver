package com.alpaytonga.CarRentwithDriver.controller;

import com.alpaytonga.CarRentwithDriver.entity.User;

import com.alpaytonga.CarRentwithDriver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<User> postDetails(@RequestBody User user) {
        // Delegate to the userService to save the user
        User savedUser = userService.saveDetails(user);

        if (savedUser != null) {
            // Return a success response with the saved user
            return ResponseEntity.ok(savedUser);
        } else {
            // Return an error response indicating that the user already exists
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


    @GetMapping("/getUser")
    public List<User> getDetails() {
        return userService.getAllDetails();
    }
    @DeleteMapping("/delete")
    public String deleteDetails(@RequestParam("user_id") long user_id) {
        userService.deleteDetails(user_id);
        return "deleted successfully";
    }


    @PutMapping("/updateUser")
    public ResponseEntity<User> putDetails(@RequestParam("id") Long id, @RequestBody User updatedUser) {
        // Check if the user with the given ID exists
        User existingUser = userService.getUserById(id);

        if (existingUser != null) {
            // Update the existing user with the new details
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setPwd(updatedUser.getPwd());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());


            // Save the updated user
            User savedUser = userService.saveDetails(existingUser);

            // Return a success response with the updated user
            return ResponseEntity.ok(savedUser);
        } else {
            // Return an error response indicating that the user was not found
            return ResponseEntity.notFound().build();
        }


    }}
