package com.example.exercise.service.impl;

import com.example.exercise.model.Product;
import com.example.exercise.repository.ProductRepository;
import com.example.exercise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DefaultProductService implements ProductService {

    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return getProductRepository().findAll();
    }

    @Override
    public Optional<Product> getProductById(final Long id) {
        return getProductRepository().findById(id);
    }

    @Override
    public Product createProduct(final Product product) {
        return getProductRepository().save(product);
    }

    @Override
    public Optional<Product> updateProduct(final Long id, final Product productDetails) {
        Optional<Product> existingProduct = getProductRepository().findById(id);

        return existingProduct.map(product -> {
            updateProductDetails(product, productDetails);
            getProductRepository().save(product);
            return product;
        });
    }

    @Override
    public boolean deleteProduct(final Long id) {
        if (getProductRepository().existsById(id)) {
            getProductRepository().deleteById(id);
            return true;
        }
        return false;
    }

    private void updateProductDetails(Product product, Product productDetails) {
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
    }

    protected ProductRepository getProductRepository() {
        return productRepository;
    }
    @Autowired
    public void setProductRepository(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
