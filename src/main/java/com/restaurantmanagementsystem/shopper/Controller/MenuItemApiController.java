package com.restaurantmanagementsystem.shopper.Controller;

import com.restaurantmanagementsystem.shopper.models.MenuItem;
import com.restaurantmanagementsystem.shopper.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController  // For REST APIs
@RequestMapping("/admin/menu/api")  // Base path for API
public class MenuItemApiController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemApiController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/list")
    public List<MenuItem> listMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @PostMapping("/add")
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        menuItemService.saveMenuItem(menuItem);
        return new ResponseEntity<>(menuItem, HttpStatus.CREATED);  // Return 201 Created
    }

    @GetMapping("/{id}")  // Get by ID
    public ResponseEntity<Optional<MenuItem>> getMenuItem(@PathVariable Long id) {
        Optional<Optional<MenuItem>> menuItem = Optional.ofNullable(menuItemService.getMenuItemById(id));
        return menuItem.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        Optional<MenuItem> existingMenuItem = menuItemService.getMenuItemById(id);
        if (existingMenuItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        menuItem.setId(id); // Ensure the ID is not changed
        menuItemService.saveMenuItem(menuItem);
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        Optional<MenuItem> existingMenuItem = menuItemService.getMenuItemById(id);
        if (existingMenuItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        menuItemService.deleteMenuItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content
    }
}