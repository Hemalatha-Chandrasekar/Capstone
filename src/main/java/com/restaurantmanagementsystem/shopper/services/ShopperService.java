package com.restaurantmanagementsystem.shopper.services;

import com.restaurantmanagementsystem.shopper.models.Shopper;
import com.restaurantmanagementsystem.shopper.repositories.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ShopperService {

    private final ShopperRepository shopperRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ShopperService(ShopperRepository shopperRepository, PasswordEncoder passwordEncoder) {
        this.shopperRepository = shopperRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Shopper registerNewShopper(Shopper shopper) {
        String encodedPassword = passwordEncoder.encode(shopper.getPassword()); // Encode the password
        shopper.setPassword(encodedPassword);
        return shopperRepository.save(shopper);
    }

    public Shopper findByUsername(String username) {
        return shopperRepository.findByUsername(username);
    }
}
