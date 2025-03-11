package com.restaurantmanagementsystem.shopper.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopperController {

    @GetMapping("/shopper")
    public String shopperMenu() {
        return "shopper/menu"; // Assuming you have a shopper/menu.html template
    }
}