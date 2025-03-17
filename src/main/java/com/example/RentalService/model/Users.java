package com.example.RentalService.model;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity class representing a User in the Rental Service system.
 */
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Unique identifier for the user
    
    private String username; // Username of the user
    private String password; // Encrypted password for authentication
    private String email; // Email address of the user
    private String role; // Role of the user (e.g., Admin, Rental, User)
    private BigInteger phoneNumber; // Contact number of the user

    /**
     * Default constructor.
     */
    public Users() {
    }

    /**
     * Parameterized constructor to initialize user details.
     * 
     * @param username    The username of the user.
     * @param password    The password of the user.
     * @param email       The email address of the user.
     * @param role        The role assigned to the user.
     * @param phoneNumber The phone number of the user.
     */
    public Users(String username, String password, String email, String role, BigInteger phoneNumber) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    // Getter and setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BigInteger getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(BigInteger phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Overrides the toString() method to return user details in a readable format.
     */
    @Override
    public String toString() {
        return "Users [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
                + role + ", phoneNumber=" + phoneNumber + "]";
    }
}
