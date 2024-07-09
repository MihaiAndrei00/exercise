package com.example.exercise.service;

import com.example.exercise.model.Cart;
import com.example.exercise.model.Product;
import com.example.exercise.model.User;

public interface CartService {
    /****
     *Get the cart giving the user
     *
     * @param user The user that cart is returned
     * @return a cart giving a user
     */
    Cart getCartByUser(User user);

    /**
     * add an item to a cart giving the user , the product and the quantity of the product
     *
     * @param user     the user who has the returned cart
     * @param product  the product that is added to the cart
     * @param quantity the quantity of that product that is added to the cart
     * @return the cart with the products added
     */
    Cart addItem(User user, Product product, int quantity);

    /**
     * Remove an item of a cart giving the User and the item id
     *
     * @param user       the user whose cart item is deleted
     * @param cartItemId the item of the cart that is deleted
     */
    void removeItem(User user, Long cartItemId);

    /****
     * Remove all items of a user cart giving the user
     *
     * @param user the user whose cart gets clean
     */
    void clearCart(User user);
}
