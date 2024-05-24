package com.example.ecommerce.services;


import com.example.ecommerce.models.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findOne(Integer id);

    List<Product> findAll();

    Product createAndUpdate(Product product);

    void delete(Integer id);

    boolean isExist(Integer id);

    List<Product> findAllByCategoryId(Integer categoryId);

    List<Product> findAllByTypePersonId(Integer typePersonId);
}
