package com.example.exercise.service;

import com.example.exercise.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    /***
     * Get the list of all products
     *
     * @return the list of all products
     */
    List<Product> getAllProducts();

    /****
     *
     * @param id the id of the product
     * @return a product giving the id
     */
    Optional<Product> getProductById(Long id);

    /****
     *
     * @param product that is created
     * @return a new product
     */
    Product createProduct(Product product);

    /****
     *  Update a product giving the id and the changes
     * @param id the id of the product which is updated
     * @param productDetails the new details of the product
     * @return the updated product
     */
    Optional<Product> updateProduct(Long id, Product productDetails);

    /****
     *
     * @param id the id of the product that is delted
     * @return a boolean (true) if the product is delted
     */
    boolean deleteProduct(Long id);
}
