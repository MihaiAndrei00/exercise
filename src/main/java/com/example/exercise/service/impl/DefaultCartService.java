package com.example.exercise.service.impl;

import com.example.exercise.model.Cart;
import com.example.exercise.model.CartItem;
import com.example.exercise.model.Product;
import com.example.exercise.model.User;
import com.example.exercise.repository.CartRepository;
import com.example.exercise.service.CartService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Getter
@Service
public class DefaultCartService implements CartService {

    private CartRepository cartRepository;

    @Override
    public Cart getCartByUser(User user) {
        return user.getCart() != null ? user.getCart() : createNewCartForUser(user);
    }

    private Cart createNewCartForUser(User user) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setItems(new ArrayList<>());
        cartRepository.save(newCart);
        user.setCart(newCart);
        return newCart;
    }

    @Override
    public Cart addItem(User user, Product product, int quantity) {
        Cart cart = getCartByUser(user);
        Optional<CartItem> existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
        }
        return getCartRepository().save(cart);
    }

    @Override
    public void removeItem(User user, Long cartItemId) {
        Cart cart = getCartByUser(user);
        cart.getItems().removeIf(item -> item.getId().equals(cartItemId));
        getCartRepository().save(cart);
    }

    @Override
    public void clearCart(User user) {
        Cart cart = getCartByUser(user);
        cart.getItems().clear();
        getCartRepository().save(cart);
    }

    @Autowired
    public void setCartRepository(final CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    protected CartRepository getCartRepository() {
        return cartRepository;
    }
}
