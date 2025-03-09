package com.restaurantmanagementsystem.shopper.repositories;

import com.restaurantmanagementsystem.shopper.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}