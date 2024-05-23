package com.example.ecommerce.services.impl;

import com.example.ecommerce.models.entities.Address;
import com.example.ecommerce.repositories.AddressRepository;
import com.example.ecommerce.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Optional<Address> findOne(Integer id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findAllByUserId(String userId) {
        return addressRepository.findAllByUserId(userId);
    }

    @Override
    public Address createAndUpdate(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(Integer id) {
        addressRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Integer id) {
        return addressRepository.existsById(id);
    }
}
