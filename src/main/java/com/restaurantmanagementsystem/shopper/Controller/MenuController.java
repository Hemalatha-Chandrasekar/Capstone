package com.restaurantmanagementsystem.shopper.Controller;

import com.restaurantmanagementsystem.shopper.models.Cart;
import com.restaurantmanagementsystem.shopper.models.CartItem;
import com.restaurantmanagementsystem.shopper.models.MenuItem;
import com.restaurantmanagementsystem.shopper.services.CartService;
import com.restaurantmanagementsystem.shopper.services.MenuService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/shopper") // Base path for all methods in this controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CartService cartService;

    @GetMapping({"/", "/menu"})
    public String showMenu(Model model, HttpSession session) {
        List<MenuItem> menuItems = menuService.getAllMenuItems();
        model.addAttribute("menuItems", menuItems);

        Long cartId = (Long) session.getAttribute("cartId");
        System.out.println("The cart id is " + cartId); // added line
        Cart cart;

        if (cartId == null) {
            System.out.println("The cart id is null, creating new cart "); // added line
            cart = new Cart();
            cart = cartService.createCart();
            session.setAttribute("cartId", cart.getId());
            System.out.println("Adding cart with id " + cart.getId() + " to session"); // added line

        } else {
            cart = cartService.getCartById(cartId);
        }

        BigDecimal total = cartService.calculateTotal(cart);
        model.addAttribute("cartTotal", total);

        return "menu";
    }

    @PostMapping("/addToCart/{menuItemId}")
    public String addToCart(@PathVariable Long menuItemId, @RequestParam(defaultValue = "1") int quantity, HttpSession session) {
        Long cartId = (Long) session.getAttribute("cartId");
        System.out.println("The cart id is " + cartId); // added line

        if (cartId == null) {
            System.out.println("The cart id is null, creating new cart "); // added line
            Cart cart = new Cart();
            cart = cartService.createCart(); // Create and persist cart
            session.setAttribute("cartId", cart.getId()); // Store in session
            cartId = cart.getId();
            System.out.println("Adding cart with id " + cart.getId() + " to session"); // added line
        }

        Cart cart = cartService.getCartById(cartId);
        MenuItem menuItem = menuService.getMenuItemById(menuItemId);

        if (menuItem != null) {
            cartService.addItemToCart(cart, menuItem, quantity);
        }

        return "redirect:/shopper/menu";
    }
    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        Long cartId = (Long) session.getAttribute("cartId");

        if (cartId == null) {
            return "redirect:/shopper/menu"; // Redirect to menu if no cart
        }

        Cart cart = cartService.getCartById(cartId);


        if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            System.out.println("Cart is empty!");
        } else {
            System.out.println("Cart contains " + cart.getCartItems().size() + " items.");
        }

        BigDecimal total = cartService.calculateTotal(cart);


        model.addAttribute("cart", cart);
        model.addAttribute("cartTotal", total);

        return "cart";
    }

    @PostMapping("/removeFromCart/{cartItemId}")
    public String removeFromCart(@PathVariable Long cartItemId, HttpSession session) {
        Long cartId = (Long) session.getAttribute("cartId");

        if (cartId != null) {
            Cart cart = cartService.getCartById(cartId);
            CartItem cartItem = cartService.getCartItemById(cartItemId);

            if (cartItem != null) { // Ensure the cart item exists
                cartService.removeItemFromCart(cart, cartItem);
            }
        }

        return "redirect:/shopper/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        Long cartId = (Long) session.getAttribute("cartId");

        if (cartId == null) {
            return "redirect:/shopper/menu";
        }

        Cart cart = cartService.getCartById(cartId);
        BigDecimal total = cartService.calculateTotal(cart);

        model.addAttribute("cart", cart);
        model.addAttribute("cartTotal", total);

        cartService.clearCart(cart);
        session.removeAttribute("cartId"); // Invalidate the cart

        return "checkout";
    }
}