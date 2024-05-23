package com.example.ecommerce.mappers.impl;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.TypePersonDTO;
import com.example.ecommerce.models.entities.TypePerson;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypePersonMapperImpl implements Mapper<TypePerson, TypePersonDTO> {
    private final ModelMapper mapper;


    @Override
    public TypePersonDTO mapTo(TypePerson typePerson) {
        return mapper.map(typePerson, TypePersonDTO.class);
    }

    @Override
    public TypePerson mapFrom(TypePersonDTO typePersonDTO) {
        return mapper.map(typePersonDTO, TypePerson.class);
    }
}
