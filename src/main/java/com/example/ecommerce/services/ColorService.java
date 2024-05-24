package com.example.ecommerce.services;

import com.example.ecommerce.models.entities.Color;

import java.util.List;
import java.util.Optional;

public interface ColorService {
    List<Color> findAll();

    List<Color> getColorByName(String name);

    Optional<Color> findOne(Integer id);

    Color createAndUpdate(Color color);

    void delete(Integer id);

    boolean isExist(Integer id);
}
