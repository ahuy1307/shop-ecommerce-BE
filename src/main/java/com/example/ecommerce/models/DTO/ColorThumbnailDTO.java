package com.example.ecommerce.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColorThumbnailDTO {
    private ColorDTO color;
    private String thumbnail;
}
