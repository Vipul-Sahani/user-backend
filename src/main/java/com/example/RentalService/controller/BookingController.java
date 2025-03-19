package com.example.RentalService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RentalService.model.Booking;
import com.example.RentalService.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

	 @Autowired
	 private BookingService bookingService;
	    
	 
	    /**
	     * Book equipment.
	     */
	    @PreAuthorize("hasAnyRole('ROLE_user')")
	    @PostMapping("/equipmentBooking")
	    public ResponseEntity<?> bookEquipment(@RequestBody Booking booking) {
	        System.out.println(booking.toString());
	        return bookingService.equipmentBooking(booking);
	    }
	    
	    
	    /**
	     * Get booking details by user ID.
	     */
	    @PreAuthorize("hasAnyRole('ROLE_user')")
	    @GetMapping("/bookingDetails/{userId}")
	    public ResponseEntity<?> getBookingDetailsByUserId(@PathVariable int userId) {
	        return bookingService.getBookingDetailsByUserId(userId);
	    }

	    /**
	     * Cancel a booking by ID.
	     */
	    @PreAuthorize("hasAnyRole('ROLE_user')")
	    @PutMapping("/cancelBooking/{bookingId}")
	    public ResponseEntity<?> cancelBooking(@PathVariable int bookingId) {
	        return bookingService.cancelBooking(bookingId);
	    }
}
