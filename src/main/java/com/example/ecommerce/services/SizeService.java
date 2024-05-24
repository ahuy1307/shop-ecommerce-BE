package com.example.ecommerce.services;


import com.example.ecommerce.models.entities.Size;

import java.util.List;
import java.util.Optional;

public interface SizeService {
    List<Size> findAll();

    Optional<Size> findOne(Integer id);

    Size createAndUpdate(Size size);

    void delete(Integer id);

    boolean isExist(Integer id);
}
