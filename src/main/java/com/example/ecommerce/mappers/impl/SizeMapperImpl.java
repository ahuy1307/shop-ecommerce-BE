package com.example.ecommerce.mappers.impl;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.SizeDTO;
import com.example.ecommerce.models.entities.Size;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SizeMapperImpl implements Mapper<Size, SizeDTO> {
    private final ModelMapper mapper;

    @Override
    public SizeDTO mapTo(Size size) {
        return mapper.map(size, SizeDTO.class);
    }

    @Override
    public Size mapFrom(SizeDTO sizeDTO) {
        return mapper.map(sizeDTO, Size.class);
    }
}
