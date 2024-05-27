package com.example.ecommerce.services;

import com.example.ecommerce.models.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findOne(Integer id);

    List<Category> findAll();

    Category createAndUpdate(Category category);

    void delete(Integer id);

    boolean isExist(Integer id);

    List<Category> findAllByTypePersonId(Integer typePersonId);

    List<Object[]> findCategoryCountByCriteria(Integer typePersonId,
                                               List<Integer> listColorId,
                                               List<Integer> listSizeId);

    List<Integer> findAllCategoryIdByTypePersonId(Integer typePersonId);
}
