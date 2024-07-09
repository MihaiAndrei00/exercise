package com.example.exercise.controller;

import com.example.exercise.model.Cart;
import com.example.exercise.model.User;
import com.example.exercise.service.CartService;
import com.example.exercise.service.ProductService;
import com.example.exercise.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;


    public CartController(final CartService cartService, final ProductService productService, final UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long userId) {
        final User user = getUserService().getUserById(userId);
        return ResponseEntity.ok(getCartService().getCartByUser(user));
    }

    @PostMapping("/add-item")
    public ResponseEntity<Cart> addItem(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        final User user = getUserService().getUserById(userId);
        return getProductService().getProductById(productId)
                .map(product -> ResponseEntity.ok(getCartService().addItem(user, product, quantity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/remove-item/{userId}/{cartItemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long userId, @PathVariable Long cartItemId) {
        final User user = getUserService().getUserById(userId);
        getCartService().removeItem(user, cartItemId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear-cart/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        final User user = getUserService().getUserById(userId);
        getCartService().clearCart(user);
        return ResponseEntity.ok().build();
    }

    protected CartService getCartService() {
        return cartService;
    }

    protected ProductService getProductService() {
        return productService;
    }

    protected UserService getUserService() {
        return userService;
    }
}
