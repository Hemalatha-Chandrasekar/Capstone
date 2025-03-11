package com.restaurantmanagementsystem.shopper.Controller;



import com.restaurantmanagementsystem.shopper.models.MenuItem;
import com.restaurantmanagementsystem.shopper.models.Shopper;
import com.restaurantmanagementsystem.shopper.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class MyController {

    private final MenuItemService menuItemService;

    @Autowired
    public MyController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }




    @GetMapping("/")
    public String home(Model model) {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems(); // Fetch menu items
        model.addAttribute("menuItems", menuItems); // Add to the model
        return "home"; // Render the home.html template
    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login"; // Custom login page
//    }




//    @GetMapping("/register")
//    public String register(Model model) {
//        model.addAttribute("user", new User()); // Add a User object to the model
//        return "register"; // Thymeleaf template: src/main/resources/templates/register.html
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute User user) {
//        // In a real application, you would:
//        // 1. Validate the user data
//        // 2. Encrypt the password
//        // 3. Save the user to the database
//        System.out.println("Registering new user: " + user.getUsername());
//        return "redirect:/login"; // Redirect to login after registration
//    }
//
//}

//@Controller
//public class MyController {
//
////    @GetMapping("/admin/menu")
////    public String adminMenu() {
////        return "admin/menu"; // Thymeleaf template for admin menu
////    }

//    @GetMapping("/shopper/menu")
//    public String shopperMenu(Model model) {
//        return "shopper/menu"; // Thymeleaf template for shopper menu
//    }
////
////    @GetMapping("/greeting")
////    public String greeting(Model model) {
////        model.addAttribute("message", "Hello, World!");  // Add data to the model
////        return "greeting";  // Return the view name
////    }
//
//    private final MenuItemService menuItemService;
//
//    @Autowired
//    public MyController(MenuItemService menuItemService) {
//        this.menuItemService = menuItemService;
//    }
//
//    @GetMapping("/")
//    public String home(Model model) {
//        List<MenuItem> menuItems = menuItemService.getAllMenuItems(); // Fetch menu items
//        model.addAttribute("menuItems", menuItems); // Add to the model
//        return "home"; // Render the home.html template
//    }
////    @GetMapping("/")
////    public String home() {
////        return "home"; // Public home page
////    }
//
//
//    @GetMapping("/login") // Keep this one
//    public String login() {
//        return "login"; // Custom login page
//    }
//
//    @GetMapping("/about")
//    public String about() {
//        return "about";
//    }
//    @GetMapping("/contact")
//    public String contact() {
//        return "contact";
//    }
}