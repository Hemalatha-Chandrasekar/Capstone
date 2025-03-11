package com.restaurantmanagementsystem.shopper.repositories;

import com.restaurantmanagementsystem.shopper.models.Shopper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {
 Shopper findByUsername(String username); // Find a shopper by username
}