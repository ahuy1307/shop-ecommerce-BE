package com.example.ecommerce.services.impl;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.SizeDTO;
import com.example.ecommerce.models.DTO.SizeQuantityDTO;
import com.example.ecommerce.models.embed.ProductSizeEmbed;
import com.example.ecommerce.models.entities.ProductSize;
import com.example.ecommerce.models.entities.Size;
import com.example.ecommerce.repositories.ProductSizeRepository;
import com.example.ecommerce.repositories.SizeRepository;
import com.example.ecommerce.services.ProductSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSizeServiceImpl implements ProductSizeService {
    private final ProductSizeRepository productSizeRepository;
    private final SizeRepository sizeRepository;
    private final Mapper<Size, SizeDTO> sizeMapper;

    @Override
    public boolean isExist(ProductSizeEmbed id) {
        return productSizeRepository.existsById(id);
    }

    @Override
    @Transactional
    public void delete(ProductSizeEmbed id) {
        productSizeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductSize createAndUpdate(ProductSize productSize) {
        return productSizeRepository.save(productSize);
    }

    @Override
    public List<ProductSize> findAllById_ProductId(Integer productId) {
        return productSizeRepository.findAllById_ProductId(productId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<SizeQuantityDTO> getSizeByProductId(Integer productId) {
        List<ProductSize> productSizes = productSizeRepository.findAllById_ProductId(productId);
        List<Integer> quantities = productSizes.stream().map(item -> item.getQuantityStock()).toList();

        List<Size> sizes = productSizes.stream().map(item -> sizeRepository.findById(item.getId().getSizeId()).orElse(null)).toList();
        List<SizeDTO> sizeDTOS = sizes.stream().map(sizeMapper::mapTo).toList();

        return sizeDTOS.stream().map(item -> {
            SizeQuantityDTO sizeQuantityDTO = new SizeQuantityDTO();
            sizeQuantityDTO.setSize(item);
            sizeQuantityDTO.setQuantityStock(quantities.get(sizeDTOS.indexOf(item)));
            return sizeQuantityDTO;
        }).toList();
    }

    @Override
    public List<Integer> findAllByTypePersonId(Integer typePersonId) {
        return productSizeRepository.findAllByTypePersonId(typePersonId);
    }

    @Override
    public List<Integer> findAllByListSizeId(List<Integer> listSizeId) {
        return productSizeRepository.findAllByListSizeId(listSizeId);
    }
}
