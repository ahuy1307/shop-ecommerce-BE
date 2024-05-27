package com.example.ecommerce.services.impl;

import com.example.ecommerce.models.entities.Category;
import com.example.ecommerce.repositories.CategoryRepository;
import com.example.ecommerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findOne(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createAndUpdate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Integer id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public List<Category> findAllByTypePersonId(Integer typePersonId) {
        return categoryRepository.findCategoryByTypePersonId(typePersonId);
    }

    @Override
    public List<Object[]> findCategoryCountByCriteria(Integer typePersonId, List<Integer> listColorId, List<Integer> listSizeId) {
        return categoryRepository.findCategoryCountByCriteria(typePersonId, listColorId, listSizeId);
    }

    @Override
    public List<Integer> findAllCategoryIdByTypePersonId(Integer typePersonId) {
        return categoryRepository.findAllCategoryIdByTypePersonId(typePersonId);
    }
}
