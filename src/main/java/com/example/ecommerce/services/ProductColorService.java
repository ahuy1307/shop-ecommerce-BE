package com.example.ecommerce.services;

import com.example.ecommerce.models.DTO.ColorThumbnailDTO;
import com.example.ecommerce.models.embed.ProductColorEmbed;
import com.example.ecommerce.models.entities.ProductColor;

import java.util.List;

public interface ProductColorService {
    boolean isExist(ProductColorEmbed id);

    void delete(ProductColorEmbed id);

    ProductColor createAndUpdate(ProductColor productColor);

    List<ProductColor> findAllById_ProductId(Integer productId);

    List<ColorThumbnailDTO> getColorByProductId(Integer productId);

    List<Object[]> findColorCountsByCriteria(Integer typePersonId,
                                             List<Integer> listSizeId, Integer categoryId);

    List<Integer> findAllColorByTypePersonId(Integer typePersonId);
}
