package com.example.RentalService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RentalService.model.Users;
import com.example.RentalService.service.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {


    @Autowired
    private UserService userService;


    /**
     * Get user details by user ID.
     */
    @GetMapping("/userDetail/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable int id) {
        return userService.getUserDetailsById(id);
    }

    /**
     * Update user details.
     */
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Users updatedUser) {
        return userService.updateUserDetails(id, updatedUser);
    }
}

