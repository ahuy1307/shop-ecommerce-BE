package com.example.ecommerce.models.entities;

import com.example.ecommerce.models.embed.ProductColorEmbed;
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
@Table(name = "product_colors")
public class ProductColor {
    @EmbeddedId
    private ProductColorEmbed id;

    private String thumbnail;
}
