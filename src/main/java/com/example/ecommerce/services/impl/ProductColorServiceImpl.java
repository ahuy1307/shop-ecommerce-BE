package com.example.ecommerce.services.impl;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.ColorDTO;
import com.example.ecommerce.models.DTO.ColorThumbnailDTO;
import com.example.ecommerce.models.embed.ProductColorEmbed;
import com.example.ecommerce.models.entities.Color;
import com.example.ecommerce.models.entities.ProductColor;
import com.example.ecommerce.repositories.ColorRepository;
import com.example.ecommerce.repositories.ProductColorRepository;
import com.example.ecommerce.services.ProductColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductColorServiceImpl implements ProductColorService {
    private final ProductColorRepository productColorRepository;
    private final ColorRepository colorRepository;
    private final Mapper<Color, ColorDTO> colorMapper;

    @Override
    public boolean isExist(ProductColorEmbed id) {
        return productColorRepository.existsById(id);
    }

    @Override
    @Transactional
    public void delete(ProductColorEmbed id) {
        productColorRepository.deleteById(id);
    }

    @Override
    public List<ProductColor> findAllById_ProductId(Integer productId) {
        return productColorRepository.findAllById_ProductId(productId);
    }

    @Override
    @Transactional
    public ProductColor createAndUpdate(ProductColor productColor) {
        return productColorRepository.save(productColor);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ColorThumbnailDTO> getColorByProductId(Integer productId) {
        List<ProductColor> productColors = productColorRepository.findAllById_ProductId(productId);
        List<String> thumbnails = productColors.stream().map(item -> item.getThumbnail()).toList();
        List<Color> colors = productColors.stream().map(item -> colorRepository.findById(item.getId().getColorId()).orElse(null)).toList();
        List<ColorDTO> colorDTOS = colors.stream().map(colorMapper::mapTo).toList();

        return colorDTOS.stream().map(item -> {
            ColorThumbnailDTO colorThumbnailDTO = new ColorThumbnailDTO();
            colorThumbnailDTO.setColor(item);
            colorThumbnailDTO.setThumbnail(thumbnails.get(colorDTOS.indexOf(item)));
            return colorThumbnailDTO;
        }).toList();
    }

    @Override
    public List<Object[]> findColorCountsByCriteria(Integer typePersonId, List<Integer> listSizeId, Integer categoryId) {
        return productColorRepository.findColorCountsByCriteria(typePersonId, listSizeId, categoryId);
    }

    @Override
    public List<Integer> findAllColorByTypePersonId(Integer typePersonId) {
        return productColorRepository.findAllColorIdByTypePersonId(typePersonId);
    }
}
