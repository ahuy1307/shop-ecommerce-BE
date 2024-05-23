package com.example.ecommerce.services;


import com.example.ecommerce.models.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Optional<Address> findOne(Integer id);

    List<Address> findAllByUserId(String userId);

    Address createAndUpdate(Address address);

    void delete(Integer id);

    boolean isExist(Integer id);
}
