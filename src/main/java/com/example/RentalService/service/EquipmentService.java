package com.example.RentalService.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.RentalService.model.Equipment;
import com.example.RentalService.model.Users;
import com.example.RentalService.repo.EquipmentRepo;
import com.example.RentalService.repo.UserRepositry;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepo equipmentRepo;
    
    @Autowired
    private UserRepositry repositry;

    private static final String IMAGE_DIRECTORY = "C:\\Users\\700054\\git\\RentalService\\RentalService\\src\\Image\\";

    /**
     * Adds a new equipment entry along with an image file.
     * @param equipment The equipment object to be added.
     * @param imageFile The image file associated with the equipment.
     * @return The saved Equipment object.
     * @throws IOException If an error occurs while storing the image.
     */
    public Equipment addEquipment(Equipment equipment, MultipartFile imageFile) throws IOException {
    	
    	int rental_id = equipment.getUser().getId();
    	
    	Users rental = repositry.findById(rental_id).get();
    	
    	equipment.setUser(rental);
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = storeImage(imageFile);
            equipment.setImageUrl(fileName);
        }
        return equipmentRepo.save(equipment);
    }

    /**
     * Stores the uploaded image file in the specified directory.
     * @param file The image file to be stored.
     * @return The generated filename.
     * @throws IOException If an error occurs while writing the file.
     */
    private String storeImage(MultipartFile file) throws IOException {
        // Ensure directory exists
        File directory = new File(IMAGE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate a unique filename
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(IMAGE_DIRECTORY, fileName);

        // Save file to disk
        Files.write(filePath, file.getBytes());

        return fileName;
    }
    
    /**
     * Retrieves the list of equipment added by a specific user.
     * @param id The user ID.
     * @return A ResponseEntity containing the list of equipment or an error message if no equipment is found.
     */
    public ResponseEntity<?> getEquipmentByUserId(int id) {
        List<Equipment> equipmentList = equipmentRepo.findByUserId(id);
        
        if (equipmentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No equipment found for user ID: " + id);
        }

        return ResponseEntity.ok(equipmentList);
    }
    
    public ResponseEntity<?> getAllEquipment() {
        List<Equipment> equipmentList = equipmentRepo.findAll();
        
        if (equipmentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No equipment found ");
        }

        return ResponseEntity.ok(equipmentList);
    }

    /**
     * Deletes an equipment entry by its ID.
     * @param id The ID of the equipment to be deleted.
     * @return A ResponseEntity containing the deleted equipment details or an error message if not found.
     */
    public ResponseEntity<?> deleteEquipmentById(int id) {
        Equipment equipment = equipmentRepo.findById(id).orElse(null);
        
        if (equipment != null) {
            equipmentRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(equipment);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No equipment found for user ID: " + id);
    }
    
    
    public ResponseEntity<?> getEquipmentById(int id){
    	
    	Equipment equipment = equipmentRepo.findById(id).orElse(null);
    	
    	if (equipment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(equipment);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No equipment found for user ID: " + id);
    }
}