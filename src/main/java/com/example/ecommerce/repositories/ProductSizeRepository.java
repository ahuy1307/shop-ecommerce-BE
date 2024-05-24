package com.example.ecommerce.repositories;

import com.example.ecommerce.models.embed.ProductSizeEmbed;
import com.example.ecommerce.models.entities.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, ProductSizeEmbed> {

    List<ProductSize> findAllById_ProductId(Integer productId);
}
