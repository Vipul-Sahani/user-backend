
package com.example.RentalService.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // The user who made the booking

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "rental_id", nullable = false)
    private Users rental; // The owner of the equipment (Rental)

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address; // Address for delivery or pickup

    @Column(nullable = false)
    private int quantity; // Quantity of equipment rented

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    // Default constructor
    public Booking() {
    }

    // Parameterized constructor
    public Booking(Users user, Users rental, Equipment equipment, Address address, int quantity,
                   LocalDate startDate, LocalDate endDate, BigDecimal totalPrice, BookingStatus status) {
        this.user = user;
        this.rental = rental;
        this.equipment = equipment;
        this.address = address;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getRental() {
        return rental;
    }

    public void setRental(Users rental) {
        this.rental = rental;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        return "Booking [bookingId=" + bookingId + ", user=" + user + ", rental=" + rental + ", equipment=" + equipment +
                ", address=" + address + ", quantity=" + quantity + ", startDate=" + startDate + 
                ", endDate=" + endDate + ", totalPrice=" + totalPrice + ", status=" + status + "]";
    }
}

