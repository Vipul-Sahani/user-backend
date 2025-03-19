package com.example.RentalService.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.RentalService.DTO.BookingDTO;
import com.example.RentalService.model.Booking;
import com.example.RentalService.model.BookingStatus;
import com.example.RentalService.model.Equipment;
import com.example.RentalService.model.Users;
import com.example.RentalService.repo.BookingRepo;
import com.example.RentalService.repo.EquipmentRepo;
import com.example.RentalService.repo.UserRepositry;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;
    
    @Autowired
    private UserRepositry userRepositry;
    
    @Autowired
    private EquipmentRepo equipmentRepo;


    /**
     * Creates a new booking.
     */


    public ResponseEntity<?> equipmentBooking(Booking booking) {
        if (booking == null) {
            return ResponseEntity.badRequest().body("Booking request cannot be null");
        }

        Equipment equipment = equipmentRepo.findById(booking.getEquipment().getEquipmentId())
                .orElse(null);

        if (equipment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipment not found");
        }

        if (equipment.getUser() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Equipment is not associated with any user");
        }

        Users user = userRepositry.findById(equipment.getUser().getId())
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        booking.setEquipment(equipment);
        booking.setRental(user);
        booking.setTotalPrice(booking.getTotalPrice());
        booking.setStatus(booking.getStatus());

        Booking savedBooking = bookingRepo.save(booking);

        // Convert to DTO
        BookingDTO bookingDTO = new BookingDTO(
            savedBooking.getBookingId(),
            savedBooking.getUser().getId(),
            savedBooking.getRental().getId(),
            savedBooking.getEquipment().getEquipmentId(),
            savedBooking.getAddress().getId(),
            savedBooking.getQuantity(),
            savedBooking.getStartDate(),
            savedBooking.getEndDate(),
            savedBooking.getTotalPrice(),
            savedBooking.getStatus()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDTO);
    }



    /**
     * Retrieves booking details by user ID.
     */
    public ResponseEntity<?> getBookingDetailsByUserId(int id) {
        List<Booking> bookings = bookingRepo.findByUserId(id);
        
        if (!bookings.isEmpty()) {
            List<BookingDTO> bookingDTOs = bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
            return ResponseEntity.ok(bookingDTOs);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No bookings found for user ID: " + id);
    }

    /**
     * Cancels a booking if it is still pending.
     */
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
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found.");
    }

    /**
     * Converts Booking entity to BookingDTO.
     */
    private BookingDTO convertToDTO(Booking booking) {
        return new BookingDTO(
            booking.getBookingId(),
            booking.getUser().getId(),
            booking.getRental().getId(),
            booking.getEquipment().getEquipmentId(),
            booking.getAddress().getId(),
            booking.getQuantity(),
            booking.getStartDate(),
            booking.getEndDate(),
            booking.getTotalPrice(),
            booking.getStatus()
        );
    }
}

