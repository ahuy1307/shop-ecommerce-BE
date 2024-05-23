package com.example.ecommerce.mappers.impl;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.CategoryDTO;
import com.example.ecommerce.models.entities.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapperImpl implements Mapper<Category, CategoryDTO> {
    private final ModelMapper mapper;

    @Override
    public CategoryDTO mapTo(Category category) {
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public Category mapFrom(CategoryDTO categoryDTO) {
        return mapper.map(categoryDTO, Category.class);
    }
}
