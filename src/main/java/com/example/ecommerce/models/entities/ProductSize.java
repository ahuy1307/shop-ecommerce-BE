package com.example.ecommerce.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Id
    @Column(name = "size_id")
    private Integer sizeId;

    @Id
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "quantity_stock")
    private Integer quantityStock;
}
