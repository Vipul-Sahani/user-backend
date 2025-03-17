package com.example.RentalService.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.RentalService.model.Users;
import com.example.RentalService.repo.UserRepositry;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthService {

    @Autowired
    private UserRepositry repo;
    
    @Autowired
    AuthenticationManager authManager;
    
    @Autowired
    JWTService jwtService;
    
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    /**
     * Registers a new user by encoding the password and saving it in the database.
     * 
     * @param user The user to be registered.
     * @return The saved user object.
     */
    public Users register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
    
    /**
     * Retrieves all users from the database.
     * 
     * @return A list of all users.
     */
    public List<Users> getAllusers(){
        return repo.findAll();
    }
    
    /**
     * Finds a user by their ID.
     * 
     * @param id The ID of the user to find.
     * @return The user object if found, otherwise null.
     */
    public Users findUsreById(int id) {
        return repo.findById(id).orElse(null);
    }
    
    /**
     * Verifies user credentials and generates a JWT token if authentication is successful.
     * 
     * @param user The user credentials.
     * @param response The HTTP response to set the JWT token cookie.
     * @return ResponseEntity containing the JWT token and success message if authentication succeeds, otherwise an error message.
     */
    public ResponseEntity<?> verify(@RequestBody Users user, HttpServletResponse response) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (authentication.isAuthenticated()) {

            Users userDemo = repo.findByEmail(user.getEmail()).get();
            String jwtToken = jwtService.generateToken(String.valueOf(userDemo.getId()), userDemo.getEmail(), userDemo.getRole().toLowerCase());

            ResponseCookie jwtCookie = ResponseCookie.from("jwtToken", jwtToken)
                    .httpOnly(false)
                    .secure(false)
                    .path("/")
                    .maxAge(86400)
                    .sameSite("Lax")
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("token", jwtToken);
            responseBody.put("message", "Login successful");

            return ResponseEntity.ok(responseBody);
        }

        return ResponseEntity.status(401).body(Map.of("error", "Invalid Credentials"));
    }
}