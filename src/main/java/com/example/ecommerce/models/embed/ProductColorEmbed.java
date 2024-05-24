package com.example.ecommerce.models.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductColorEmbed implements Serializable {
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "color_id")
    private Integer colorId;
}
