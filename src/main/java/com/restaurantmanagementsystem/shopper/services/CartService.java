
package com.restaurantmanagementsystem.shopper.services;

import com.restaurantmanagementsystem.shopper.models.Cart;
import com.restaurantmanagementsystem.shopper.models.CartItem;
import com.restaurantmanagementsystem.shopper.models.MenuItem;
import com.restaurantmanagementsystem.shopper.repositories.CartItemRepository;
import com.restaurantmanagementsystem.shopper.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart getCartById(Long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        return cartOptional.orElseGet(() -> {
            Cart newCart = new Cart();
            return cartRepository.save(newCart);
        });
    }

    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    public Cart addItemToCart(Cart cart, MenuItem menuItem, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setMenuItem(menuItem);
        cartItem.setQuantity(quantity);
        cart.addItem(cartItem);
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Cart cart, CartItem cartItem) {
        cart.removeItem(cartItem);
        cartItemRepository.delete(cartItem); // Remove the CartItem from the database
        return cartRepository.save(cart);
    }

    public BigDecimal calculateTotal(Cart cart) {
        System.out.println("Calculating total for cart: " + cart.getId());
        BigDecimal total = cart.getCartItems().stream()
                .map(item -> {
                    BigDecimal itemPrice = item.getMenuItem().getPrice();
                    int quantity = item.getQuantity();
                    BigDecimal itemTotal = itemPrice.multiply(BigDecimal.valueOf(quantity));
                    System.out.println("Item: " + item.getMenuItem().getName() + ", Price: " + itemPrice + ", Quantity: " + quantity + ", Item Total: " + itemTotal);
                    return itemTotal;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total amount" + total);
        return total;
    }

    public Cart clearCart(Cart cart) {
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }

    public Cart createCart() {
        Cart newCart = new Cart();
        return cartRepository.save(newCart);
    }

}


