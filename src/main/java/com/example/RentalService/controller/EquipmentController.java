package com.example.RentalService.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.RentalService.model.Equipment;
import com.example.RentalService.service.EquipmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/equipment")
@CrossOrigin
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    /**
     * Adds new equipment along with an image file.
     * @param equipmentJson JSON string containing equipment details.
     * @param imageFile Image file to be uploaded.
     * @return Response entity containing saved equipment or error message.
     */
    @PostMapping("/addEquipment")
    public ResponseEntity<?> addEquipment(@RequestPart("equipment") String equipmentJson,
                                          @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            // Convert JSON string to Equipment object
            ObjectMapper objectMapper = new ObjectMapper();
            Equipment equipment = objectMapper.readValue(equipmentJson, Equipment.class);

            Equipment savedEquipment = equipmentService.addEquipment(equipment, imageFile);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    /**
     * Retrieves equipment details by user ID.
     * @param id The user ID.
     * @return Response entity with a list of equipment or an error message.
     */
    @GetMapping("/getEquipment/{id}")
    public ResponseEntity<?> getEquipmentByUserId(@PathVariable int id) {
        return equipmentService.getEquipmentByUserId(id);
    }

    @GetMapping("/getAllEquipment")
    public ResponseEntity<?> getAllEquipment(){
    	return equipmentService.getAllEquipment();
    }
    /**
     * Retrieves an image file by its filename.
     * @param filename Name of the image file.
     * @return Response entity containing the image resource or a not found response.
     * @throws MalformedURLException If the file path is incorrect.
     */
    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws MalformedURLException {
        Path imagePath = Paths.get("C:/Users/700054/git/RentalService/RentalService/src/Image/" + filename);
        Resource resource = new UrlResource(imagePath.toUri());

        if (resource.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Change if using PNG, etc.
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an equipment record by its ID.
     * @param id The equipment ID to delete.
     * @return Response entity with success or failure status.
     */
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        return equipmentService.deleteEquipmentById(id);
    }
}
