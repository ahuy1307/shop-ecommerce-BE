package com.example.ecommerce.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColorAndQuantityDTO {
    private ColorDTO color;
    private Integer quantity;
}
