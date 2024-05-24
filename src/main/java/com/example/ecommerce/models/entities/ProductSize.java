package com.example.ecommerce.models.entities;

import com.example.ecommerce.models.embed.ProductSizeEmbed;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_sizes")
public class ProductSize {
    @EmbeddedId
    private ProductSizeEmbed id;

    @Column(name = "quantity_stock")
    private Integer quantityStock;
}
