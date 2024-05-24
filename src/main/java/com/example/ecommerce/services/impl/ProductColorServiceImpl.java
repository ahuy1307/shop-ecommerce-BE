package com.example.ecommerce.services.impl;

import com.example.ecommerce.models.DTO.ColorThumbnailDTO;
import com.example.ecommerce.models.embed.ProductColorEmbed;
import com.example.ecommerce.models.entities.ProductColor;
import com.example.ecommerce.repositories.ColorRepository;
import com.example.ecommerce.repositories.ProductColorRepository;
import com.example.ecommerce.services.ProductColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductColorServiceImpl implements ProductColorService {
    private final ProductColorRepository productColorRepository;
    private final ColorRepository colorRepository;

    @Override
    public boolean isExist(ProductColorEmbed id) {
        return productColorRepository.existsById(id);
    }

    @Override
    public void delete(ProductColorEmbed id) {

    }

    @Override
    public ProductColor createAndUpdate(ProductColor productColor) {
        return null;
    }

    @Override
    public List<ColorThumbnailDTO> getColorByProductId(Integer productId) {
        return List.of();
    }
}
