package com.example.ecommerce.services.impl;

import com.example.ecommerce.models.entities.Size;
import com.example.ecommerce.repositories.SizeRepository;
import com.example.ecommerce.services.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Optional<Size> findOne(Integer id) {
        return sizeRepository.findById(id);
    }

    @Override
    public Size createAndUpdate(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public void delete(Integer id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Integer id) {
        return sizeRepository.existsById(id);
    }
}
