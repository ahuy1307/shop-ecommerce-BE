package com.example.ecommerce.mappers.impl;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.ProductDTO;
import com.example.ecommerce.models.entities.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements Mapper<Product, ProductDTO> {
    private final ModelMapper mapper;

    @Override
    public ProductDTO mapTo(Product product) {
        return mapper.map(product, ProductDTO.class);
    }

    @Override
    public Product mapFrom(ProductDTO productDTO) {
        return mapper.map(productDTO, Product.class);
    }
}
