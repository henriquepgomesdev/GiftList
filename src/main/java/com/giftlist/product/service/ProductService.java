package com.giftlist.product.service;

import com.giftlist.product.domain.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product saveProduct(Product product);

    Optional<Product> findProductById(Long id);

    List<Product> findAllProducts();

    void updateProductStock(Long productId, Integer newStock);

    void updateProductPrice(Long productId, BigDecimal newPrice);

    void deleteProduct(Long productId);
}