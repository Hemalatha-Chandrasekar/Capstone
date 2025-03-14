package com.restaurantmanagementsystem.shopper.repositories;

import com.restaurantmanagementsystem.shopper.models.Cart;
import com.restaurantmanagementsystem.shopper.models.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Repository
    interface UserRepository extends JpaRepository<ShopperRepository, Long> {
    }
}