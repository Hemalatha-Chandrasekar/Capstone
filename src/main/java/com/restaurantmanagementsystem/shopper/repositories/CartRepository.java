package com.restaurantmanagementsystem.shopper.repositories;

import com.restaurantmanagementsystem.shopper.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}