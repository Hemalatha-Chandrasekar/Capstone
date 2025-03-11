package com.restaurantmanagementsystem.shopper.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

  @GetMapping("/contact")
    public String contact() {
        return "contact"; // Returns the 'about.html' template
   }
}