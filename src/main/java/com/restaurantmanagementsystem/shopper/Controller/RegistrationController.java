package com.restaurantmanagementsystem.shopper.Controller;

import com.restaurantmanagementsystem.shopper.models.Shopper;
import com.restaurantmanagementsystem.shopper.repositories.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:9090")
public class RegistrationController {

//    @Autowired
//    private ShopperRepository shopperRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;


    private final ShopperRepository shopperRepository;
    private final PasswordEncoder passwordEncoder;  // Use PasswordEncoder

    @Autowired
    public RegistrationController(ShopperRepository shopperRepository, PasswordEncoder passwordEncoder) { // Constructor injection
        this.shopperRepository = shopperRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerShopper(@RequestBody Shopper shopper) {
        // Basic validation
        if (shopper.getUsername() == null || shopper.getUsername().isEmpty() ||
                shopper.getPassword() == null || shopper.getPassword().isEmpty()) {
            return new ResponseEntity<>("Username and password are required.", HttpStatus.BAD_REQUEST);
        }

        // Check if username already exists
        if (shopperRepository.findByUsername(shopper.getUsername()) != null) {
            return new ResponseEntity<>("Username already exists.", HttpStatus.BAD_REQUEST);
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(shopper.getPassword());
        shopper.setPassword(hashedPassword);

        // Set default role for new shoppers
        shopper.setRole("SHOPPER");

        // Save the shopper to the database
        Shopper savedShopper = shopperRepository.save(shopper);
        return new ResponseEntity<>(savedShopper, HttpStatus.CREATED);
    }
}