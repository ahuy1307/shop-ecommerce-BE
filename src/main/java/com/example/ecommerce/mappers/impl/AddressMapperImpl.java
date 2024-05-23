package com.example.ecommerce.mappers.impl;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.AddressDTO;
import com.example.ecommerce.models.entities.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapperImpl implements Mapper<Address, AddressDTO> {
    private final ModelMapper mapper;

    @Override
    public AddressDTO mapTo(Address address) {
        return mapper.map(address, AddressDTO.class);
    }

    @Override
    public Address mapFrom(AddressDTO addressDTO) {
        return mapper.map(addressDTO, Address.class);
    }
}
