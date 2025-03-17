package com.example.RentalService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.RentalService.model.Booking;
import com.example.RentalService.model.BookingStatus;
import com.example.RentalService.repo.BookingRepo;

@Service
public class BookingService {

	
	@Autowired
	private BookingRepo bookingRepo;
	
	
	public ResponseEntity<?> equipmentBooking(Booking booking){
		
		return ResponseEntity.ok(bookingRepo.save(booking));
	}
	
	public ResponseEntity<?> getBookingDetailsByUserId(int id){
		
		List<Booking> bookings = bookingRepo.findByUserId(id);
		
		if(bookings != null) {
			return ResponseEntity.ok(bookings);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Booking found for user ID: " + id);
	}
	
	public ResponseEntity<?> cancelBooking(int bookingId) {
	    Optional<Booking> bookingOptional = bookingRepo.findById(bookingId);
	    if (bookingOptional.isPresent()) {
	        Booking booking = bookingOptional.get();
	        if (booking.getStatus() == BookingStatus.PENDING) {
	            booking.setStatus(BookingStatus.CANCELLED);
	            bookingRepo.save(booking);
	            return ResponseEntity.ok("Booking Cancelled Successfully");
	        } else {
	            return ResponseEntity.badRequest().body("Only pending bookings can be cancelled.");
	        }
	    }
	    return ResponseEntity.notFound().build();
	}

}
