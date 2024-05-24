package com.example.ecommerce.mappers.impl;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.ColorDTO;
import com.example.ecommerce.models.entities.Color;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ColorMapperImpl implements Mapper<Color, ColorDTO> {
    private final ModelMapper mapper;

    @Override
    public ColorDTO mapTo(Color color) {
        return mapper.map(color, ColorDTO.class);
    }

    @Override
    public Color mapFrom(ColorDTO colorDTO) {
        return mapper.map(colorDTO, Color.class);
    }
}
