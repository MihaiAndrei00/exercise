package com.example.exercise.service;

import com.example.exercise.model.Cart;
import com.example.exercise.model.CartItem;
import com.example.exercise.model.Product;
import com.example.exercise.model.User;
import com.example.exercise.repository.CartRepository;
import com.example.exercise.repository.ProductRepository;
import com.example.exercise.repository.UserRepository;
import com.example.exercise.service.impl.DefaultCartService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DefaultCartServiceTest {
    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DefaultCartService cartService;

    public DefaultCartServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCartByUser() {
        User user = new User();
        Cart cart = new Cart();
        user.setCart(cart);

        Cart result = cartService.getCartByUser(user);

        assertEquals(cart, result);
    }

    @Test
    void testAddItemToCartWhenItemDoesNotExist() {
        User user = new User();
        Cart cart = new Cart();
        cart.setItems(new ArrayList<>());
        user.setCart(cart);

        Product product = new Product();
        product.setId(1L);

        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        cartService.addItem(user, product, 2);

        assertEquals(1, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).getQuantity());
        assertEquals(product.getId(), cart.getItems().get(0).getProduct().getId());
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testAddItemToCartWhenItemExists() {
        User user = new User();
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(new Product());
        cartItem.getProduct().setId(1L);
        cartItem.setQuantity(2);
        cart.setItems(new ArrayList<>());
        cart.getItems().add(cartItem);
        user.setCart(cart);

        Product product = new Product();
        product.setId(1L);

        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        cartService.addItem(user, product, 3);

        assertEquals(1, cart.getItems().size());
        assertEquals(5, cart.getItems().get(0).getQuantity());
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testRemoveItem() {
        User user = new User();
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cart.setItems(new ArrayList<>());
        cart.getItems().add(cartItem);
        user.setCart(cart);

        cartService.removeItem(user, 1L);

        assertTrue(cart.getItems().isEmpty());
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testClearCart() {
        User user = new User();
        Cart cart = new Cart();
        cart.setItems(new ArrayList<>());
        cart.getItems().add(new CartItem());
        user.setCart(cart);

        cartService.clearCart(user);

        assertTrue(cart.getItems().isEmpty());
        verify(cartRepository, times(1)).save(cart);
    }

}
