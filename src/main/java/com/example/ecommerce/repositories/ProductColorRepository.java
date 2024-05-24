package com.example.ecommerce.repositories;

import com.example.ecommerce.models.embed.ProductColorEmbed;
import com.example.ecommerce.models.entities.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, ProductColorEmbed> {

    List<ProductColor> findAllById_ProductId(Integer productId);
}
