package com.example.RentalService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.RentalService.model.Equipment;
import com.example.RentalService.model.Users;
import com.example.RentalService.repo.EquipmentRepo;
import com.example.RentalService.repo.UserRepositry;

@Service
public class UserService {
	
	@Autowired
    private EquipmentRepo equipmentRepo;
	
	@Autowired
	private UserRepositry userRepo;
	
	public ResponseEntity<?> getAllEquipment() {
        List<Equipment> equipmentList = equipmentRepo.findAll();
        
        if (equipmentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No equipment found ");
        }

        return ResponseEntity.ok(equipmentList);
    }
	
	
	public ResponseEntity<?> getUserDetailsById(int id){
		Users user = userRepo.findById(id).orElse(null);
		
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User Found");
	}
	
	 public ResponseEntity<?> updateUserDetails(int id, Users updatedUser) {
		 
		 	Users user = userRepo.findById(id).get();
		 	
		 	if(user != null) {
		 		
		 		user.setEmail(updatedUser.getEmail());
		 		user.setPhoneNumber(updatedUser.getPhoneNumber());
		 		user.setUsername(updatedUser.getUsername());
		 		
		 		userRepo.save(user);
		 		
		 	return	ResponseEntity.ok(user);
		 	}
	        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User Found");
	    }

}
