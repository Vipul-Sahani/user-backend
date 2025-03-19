package com.example.RentalService.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.RentalService.model.BookingStatus;

public class BookingDTO {
    
    private int bookingId;
    private int userId;       // ID of the user who booked
    private int rentalId;     // ID of the rental owner
    private int equipmentId;  // ID of the equipment
    private int addressId;    // ID of the address used
    private int quantity;     // Quantity of equipment rented
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalPrice;
    private BookingStatus status;

    // Default constructor
    public BookingDTO() {
    }

    // Parameterized constructor
    public BookingDTO(int bookingId, int userId, int rentalId, int equipmentId, int addressId, int quantity, 
                      LocalDate startDate, LocalDate endDate, BigDecimal totalPrice, BookingStatus status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.rentalId = rentalId;
        this.equipmentId = equipmentId;
        this.addressId = addressId;
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookingDTO [bookingId=" + bookingId + ", userId=" + userId + ", rentalId=" + rentalId +
                ", equipmentId=" + equipmentId + ", addressId=" + addressId + ", quantity=" + quantity +
                ", startDate=" + startDate + ", endDate=" + endDate + ", totalPrice=" + totalPrice +
                ", status=" + status + "]";
    }
}
