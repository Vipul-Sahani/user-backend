package com.example.RentalService.DTO;

import java.math.BigInteger;

public class UserRegisterDTO {
    private String username;
    private String email;
    private String password;
    private String role;
    private BigInteger phoneNumber;

    public UserRegisterDTO() {}

    public UserRegisterDTO(String username, String email, String password, String role, BigInteger phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public BigInteger getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(BigInteger phoneNumber) { this.phoneNumber = phoneNumber; }
}
