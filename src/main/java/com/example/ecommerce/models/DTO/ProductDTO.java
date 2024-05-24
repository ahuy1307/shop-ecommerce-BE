package com.example.ecommerce.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Integer id;
    private String name;
    private float price;
    private List<ColorThumbnailDTO> colors;
    private List<SizeQuantityDTO> sizes;
    private String composition;
    private String neckline;
    private String sleeveLength;
    private String careInstruction;
    private Integer categoryId;
}
