package com.example.ecommerce.services.impl;

import com.example.ecommerce.models.entities.Product;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllByTypePersonId(Integer typePersonId) {
        return productRepository.findAllByTypePersonId(typePersonId);
    }

    @Override
    public Optional<Product> findOne(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product createAndUpdate(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Integer id) {
        return productRepository.existsById(id);
    }

    @Override
    public List<Product> findAllByCategoryId(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }
}
