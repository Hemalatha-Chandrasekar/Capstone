package com.restaurantmanagementsystem.shopper.Controller;

import com.restaurantmanagementsystem.shopper.models.Category;
import com.restaurantmanagementsystem.shopper.models.MenuItem;
import com.restaurantmanagementsystem.shopper.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/menu") // Add this RequestMapping
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping // Remove from here
    public String listMenuItems(Model model) {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        model.addAttribute("menuItems", menuItems);
        return "menu/list"; // Thymeleaf template:  src/main/resources/templates/menu/list.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        model.addAttribute("categories", Category.values());
        return "menu/add"; // Thymeleaf template: src/main/resources/templates/menu/add.html
    }

    @PostMapping("/add")
    public String addMenuItem(@ModelAttribute MenuItem menuItem) {
        menuItemService.saveMenuItem(menuItem);
        return "redirect:/admin/menu";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<MenuItem> menuItem = menuItemService.getMenuItemById(id);
        if (menuItem.isPresent()) {
            model.addAttribute("menuItem", menuItem.get());
            model.addAttribute("categories", Category.values());
            return "menu/edit"; // Thymeleaf template: src/main/resources/templates/menu/edit.html
        } else {
            return "redirect:/admin/menu"; // Or show an error message
        }
    }

    @PostMapping("/edit/{id}")
    public String updateMenuItem(@PathVariable Long id, @ModelAttribute MenuItem menuItem) {
        menuItem.setId(id); // Ensure the ID is set for the update
        menuItemService.saveMenuItem(menuItem);
        return "redirect:/admin/menu";
    }

    @GetMapping("/delete/{id}")
    public String deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return "redirect:/admin/menu";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "redirect:/admin/menu"; //added temporary fix to get the code to run
    }
}