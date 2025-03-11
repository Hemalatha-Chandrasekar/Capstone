package com.restaurantmanagementsystem.shopper.services;

import com.restaurantmanagementsystem.shopper.models.MenuItem;
import com.restaurantmanagementsystem.shopper.repositories.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMenuItems_ShouldReturnListOfMenuItems() {
        // Arrange
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setId(1L);
        menuItem1.setName("Burger");
        menuItem1.setPrice(BigDecimal.valueOf(10.0));

        MenuItem menuItem2 = new MenuItem();
        menuItem2.setId(2L);
        menuItem2.setName("Pizza");
        menuItem2.setPrice(BigDecimal.valueOf(12.0));

        List<MenuItem> expectedMenuItems = Arrays.asList(menuItem1, menuItem2);
        when(menuItemRepository.findAll()).thenReturn(expectedMenuItems);

        // Act
        List<MenuItem> actualMenuItems = menuService.getAllMenuItems();

        // Assert
        assertEquals(expectedMenuItems.size(), actualMenuItems.size());
        assertEquals(expectedMenuItems, actualMenuItems);
        verify(menuItemRepository, times(1)).findAll();
    }

    @Test
    void getMenuItemById_ShouldReturnMenuItem_WhenIdExists() {
        // Arrange
        Long menuItemId = 1L;
        MenuItem expectedMenuItem = new MenuItem();
        expectedMenuItem.setId(menuItemId);
        expectedMenuItem.setName("Burger");
        expectedMenuItem.setPrice(BigDecimal.valueOf(10.0));

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(expectedMenuItem));

        // Act
        MenuItem actualMenuItem = menuService.getMenuItemById(menuItemId);

        // Assert
        assertEquals(expectedMenuItem, actualMenuItem);
        verify(menuItemRepository, times(1)).findById(menuItemId);
    }

    @Test
    void getMenuItemById_ShouldReturnNull_WhenIdDoesNotExist() {
        // Arrange
        Long menuItemId = 1L;
        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.empty());

        // Act
        MenuItem actualMenuItem = menuService.getMenuItemById(menuItemId);

        // Assert
        assertNull(actualMenuItem);
        verify(menuItemRepository, times(1)).findById(menuItemId);
    }

    @Test
    void saveMenuItem_ShouldReturnSavedMenuItem() {
        // Arrange
        MenuItem menuItemToSave = new MenuItem();
        menuItemToSave.setName("Burger");
        menuItemToSave.setPrice(BigDecimal.valueOf(10.0));

        MenuItem savedMenuItem = new MenuItem();
        savedMenuItem.setId(1L); // Simulate database assigning an ID
        savedMenuItem.setName("Burger");
        savedMenuItem.setPrice(BigDecimal.valueOf(10.0));

        when(menuItemRepository.save(menuItemToSave)).thenReturn(savedMenuItem);

        // Act
        MenuItem actualMenuItem = menuService.saveMenuItem(menuItemToSave);

        // Assert
        assertEquals(savedMenuItem, actualMenuItem);
        verify(menuItemRepository, times(1)).save(menuItemToSave);
    }

    @Test
    void deleteMenuItem_ShouldDeleteMenuItem_WhenIdExists() {
        // Arrange
        Long menuItemId = 1L;

        // Act
        menuService.deleteMenuItem(menuItemId);

        // Assert
        verify(menuItemRepository, times(1)).deleteById(menuItemId);
    }
}