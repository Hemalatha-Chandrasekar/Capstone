package com.restaurantmanagementsystem.shopper.repositories;


import com.restaurantmanagementsystem.shopper.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}