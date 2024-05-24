package com.example.ecommerce.services.impl;

import com.example.ecommerce.models.entities.Color;
import com.example.ecommerce.repositories.ColorRepository;
import com.example.ecommerce.services.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Optional<Color> findOne(Integer id) {
        return colorRepository.findById(id);
    }

    @Override
    public Color createAndUpdate(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public void delete(Integer id) {
        colorRepository.deleteById(id);
    }

    @Override
    public List<Color> getColorByName(String name) {
        return colorRepository.getColorByName(name);
    }

    @Override
    public boolean isExist(Integer id) {
        return colorRepository.existsById(id);
    }
}
