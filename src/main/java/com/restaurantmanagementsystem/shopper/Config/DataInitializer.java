package com.restaurantmanagementsystem.shopper.Config;

import com.restaurantmanagementsystem.shopper.models.MenuItem;
import com.restaurantmanagementsystem.shopper.repositories.MenuItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public void run(String... args) {
        if (menuItemRepository.count() == 0) {
            MenuItem burger = new MenuItem();
            burger.setName("Burger");
            burger.setDescription("Classic beef burger with lettuce and tomato");
            burger.setPrice(new BigDecimal("8.99"));
            burger.setImageUrl("/images/burger.jpg");
            menuItemRepository.save(burger);
            logger.info("Saved burger menu item.");

            MenuItem pizza = new MenuItem();
            pizza.setName("Pizza");
            pizza.setDescription("Delicious pepperoni pizza");
            pizza.setPrice(new BigDecimal("12.50"));
            pizza.setImageUrl("/images/pizza.jpg");
            menuItemRepository.save(pizza);
            logger.info("Saved pizza menu item.");

            MenuItem spaghetti = new MenuItem();
            spaghetti.setName("spaghetti");
            spaghetti.setDescription("Fresh spaghetti");
            spaghetti.setPrice(new BigDecimal("6.75"));
            spaghetti.setImageUrl("/images/spaghetti.jpg");
            menuItemRepository.save(spaghetti);
            logger.info("Saved spaghetti menu item.");

            MenuItem fries = new MenuItem();
            fries.setName("fries");
            fries.setDescription("Fresh fries");
            fries.setPrice(new BigDecimal("6.75"));
            fries.setImageUrl("/images/fries.jpg");
            menuItemRepository.save(fries);
            logger.info("Saved fries menu item.");

            logger.info("Menu items initialized.");
        } else {
            logger.info("Menu items already exist.");
        }
    }
}