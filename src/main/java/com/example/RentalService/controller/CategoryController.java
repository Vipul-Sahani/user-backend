package com.example.RentalService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RentalService.model.Category;
import com.example.RentalService.model.Users;
import com.example.RentalService.service.CategoryService;
import com.example.RentalService.service.UserService;

@RestController
@RequestMapping("/api/rental/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private UserService userService;

    /**
     * Adds a new category for a specific user.
     * @param category The category details.
     * @return Response entity with the saved category or error message.
     */
    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {

        if (category.getUser() == null || category.getUser().getId() <= 0) {
            return ResponseEntity.badRequest().body("Valid User ID is required");
        }

        Users user = userService.findUsreById(category.getUser().getId());
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        category.setUser(user);
        Category savedCategory = categoryService.addCategory(category);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    /**
     * Checks the authenticated user's role.
     * @return The username and assigned roles.
     */
    @GetMapping("/check-role")
    public String checkRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "User: " + authentication.getName() + ", Roles: " + authentication.getAuthorities();
    }

    /**
     * Deletes a category by its ID.
     * @param id The category ID to delete.
     * @return Response entity with success or failure status.
     */
    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }

    /**
     * Retrieves all categories.
     * @return Response entity with a list of all categories.
     */
    @GetMapping("/getAllCategory")
    public ResponseEntity<List<Category>> getAllCategory() {
        return new ResponseEntity<>(categoryService.getAllcategory(), HttpStatus.OK);
    }

    /**
     * Retrieves categories associated with a specific user.
     * @param id The user ID.
     * @return List of categories belonging to the specified user.
     */
    @GetMapping("/category/{id}")
    public List<Category> getCategoryByUser(@PathVariable int id) {
        return categoryService.getCategoryByUserId(id);
    }
}
