package com.example.ecommerce.controllers;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.CategoryDTO;
import com.example.ecommerce.models.entities.Category;
import com.example.ecommerce.services.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    private final Mapper<Category, CategoryDTO> categoryMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getOneCategory(@PathVariable Integer id) {
        Category category = categoryService.findOne(id).orElse(null);

        if (category == null)
            return ResponseEntity.notFound().build();
        return new ResponseEntity<>(categoryMapper.mapTo(category), HttpStatus.OK);
    }

    @GetMapping
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryService.findAll();

        return categories.stream().map(categoryMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping("/person/{typePersonId}")
    public List<CategoryDTO> getAllCategoryByTypePersonId(@PathVariable Integer typePersonId) {
        List<Category> categories = categoryService.findAllByTypePersonId(typePersonId);

        return categories.stream().map(categoryMapper::mapTo).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryMapper.mapFrom(categoryDTO);
        Category createdCategory = categoryService.createAndUpdate(category);

        return new ResponseEntity<>(categoryMapper.mapTo(createdCategory), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        if (!categoryService.isExist(id))
            return ResponseEntity.notFound().build();

        Category category = categoryMapper.mapFrom(categoryDTO);
        category.setId(id);
        Category updatedCategory = categoryService.createAndUpdate(category);

        return new ResponseEntity<>(categoryMapper.mapTo(updatedCategory), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        if (!categoryService.isExist(id))
            return ResponseEntity.notFound().build();

        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
