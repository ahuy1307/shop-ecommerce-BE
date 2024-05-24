package com.example.ecommerce.services;

import com.example.ecommerce.models.DTO.SizeQuantityDTO;
import com.example.ecommerce.models.embed.ProductSizeEmbed;
import com.example.ecommerce.models.entities.ProductSize;

import java.util.List;

public interface ProductSizeService {
    boolean isExist(ProductSizeEmbed id);

    void delete(ProductSizeEmbed id);

    ProductSize createAndUpdate(ProductSize productSize);

    List<ProductSize> findAllById_ProductId(Integer productId);

    List<SizeQuantityDTO> getSizeByProductId(Integer productId);

    List<Integer> findAllByTypePersonId(Integer typePersonId);

    List<Integer> findAllByListSizeId(List<Integer> listSizeId);
}
