package com.example.ecommerce.models.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSizeEmbed {
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "size_id")
    private Integer sizeId;
}
