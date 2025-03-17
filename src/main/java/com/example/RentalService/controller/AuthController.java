package com.example.RentalService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.RentalService.model.Users;
import com.example.RentalService.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService service;
    
    @Autowired
    HttpServletResponse response;

    /**
     * Handles user registration.
     * @param user User details for registration.
     * @return Registered user details.
     */
    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);
    }

    /**
     * Home page endpoint.
     * @return A simple welcome message.
     */
    @GetMapping("/")
    public String homePage() {
        return "Hello...";
    }

    /**
     * Checks the authenticated user's role.
     * @return The username and their assigned roles.
     */
    @GetMapping("/check-role")
    public String checkRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "User: " + authentication.getName() + ", Roles: " + authentication.getAuthorities();
    }

    /**
     * Handles user login.
     * @param user User credentials for login.
     * @return Response entity with authentication status.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        return service.verify(user, response);
    }
}
