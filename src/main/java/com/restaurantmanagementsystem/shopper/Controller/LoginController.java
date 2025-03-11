package com.restaurantmanagementsystem.shopper.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // Use @Controller, not @RestController, to serve Thymeleaf templates
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "Login"; // Returns the name of the Thymeleaf template
    }
    @GetMapping("/register")
    public String showregisterPage() {
        return "register"; // Returns the name of the Thymeleaf template
    }
}