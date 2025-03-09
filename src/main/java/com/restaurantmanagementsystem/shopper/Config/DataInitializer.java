package com.restaurantmanagementsystem.shopper.Config;

import com.restaurantmanagementsystem.shopper.models.MenuItem;
import com.restaurantmanagementsystem.shopper.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if menu items already exist to avoid duplicates
        if (menuItemRepository.count() == 0) {
            MenuItem burger = new MenuItem();
            burger.setName("Burger");
            burger.setDescription("Classic beef burger with lettuce and tomato");
            burger.setPrice(new BigDecimal("8.99"));
            burger.setImageUrl("https://example.com/burger.jpg");
            menuItemRepository.save(burger);

            MenuItem pizza = new MenuItem();
            pizza.setName("Pizza");
            pizza.setDescription("Delicious pepperoni pizza");
            pizza.setPrice(new BigDecimal("12.50"));
            pizza.setImageUrl("https://example.com/pizza.jpg");
            menuItemRepository.save(pizza);

            MenuItem salad = new MenuItem();
            salad.setName("Salad");
            salad.setDescription("Fresh garden salad with vinaigrette");
            salad.setPrice(new BigDecimal("6.75"));
            salad.setImageUrl("https://example.com/salad.jpg");
            menuItemRepository.save(salad);

            System.out.println("Menu items initialized.");
        } else {
            System.out.println("Menu items already exist.");
        }
    }
}