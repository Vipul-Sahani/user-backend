package com.example.RentalService.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RentalService.model.Booking;
import com.example.RentalService.model.Users;
import com.example.RentalService.service.BookingService;
import com.example.RentalService.service.EquipmentService;
import com.example.RentalService.service.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {

	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserService userService;

	@GetMapping("/getAllEquipment")
	public ResponseEntity<?> getAllEquipment() {
		return equipmentService.getAllEquipment();
	}

	@GetMapping("/{filename}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename) throws MalformedURLException {
		Path imagePath = Paths.get("C:/Users/700054/git/RentalService/RentalService/src/Image/" + filename);
		Resource resource = new UrlResource(imagePath.toUri());

		if (resource.exists()) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG) // Change if using PNG, etc.
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/getEquipmnetById/{id}")
	public ResponseEntity<?> getEquipmnetById(@PathVariable int id) {
		return equipmentService.getEquipmentById(id);
	}

	@GetMapping("/userDetail/{id}")
	public ResponseEntity<?> getUserDetails(@PathVariable int id) {
		return userService.getUserDetailsById(id);
	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Users updatedUser) {
		return userService.updateUserDetails(id, updatedUser);
	}
	
	@PostMapping("/equipmentBooking")
	public ResponseEntity<?> equipmentBooking(@RequestBody Booking booking){
		System.out.println(booking.toString());
		return bookingService.equipmentBooking(booking);
	}
	
	@GetMapping("/getBookingDetails/{id}")
	public ResponseEntity<?> getBookingDetailsByUserId(@PathVariable int id){
		return bookingService.getBookingDetailsByUserId(id);
	}
	
	@PutMapping("/cancelBooking/{bookingId}")
	public ResponseEntity<?> cancelBooking(@PathVariable int bookingId) {
		return bookingService.cancelBooking(bookingId);
	}
}
