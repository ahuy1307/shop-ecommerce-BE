package com.example.ecommerce.controllers;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.CategoryAndQuantityDTO;
import com.example.ecommerce.models.DTO.CategoryDTO;
import com.example.ecommerce.models.DTO.ResponseMessage;
import com.example.ecommerce.models.entities.Category;
import com.example.ecommerce.services.impl.CategoryServiceImpl;
import com.example.ecommerce.services.impl.TypePersonServiceImpl;
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
    private final TypePersonServiceImpl typePersonService;

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
    public List<CategoryAndQuantityDTO> getAllCategoryByTypePersonId(@PathVariable Integer typePersonId,
                                                                     @RequestParam(name = "color", required = false) List<Integer> listColorId,
                                                                     @RequestParam(name = "size", required = false) List<Integer> listSizeId) {
        List<Object[]> categoryAndQuantity = categoryService.findCategoryCountByCriteria(typePersonId, listColorId, listSizeId);
        List<Integer> categoryIds = categoryService.findAllCategoryIdByTypePersonId(typePersonId);

        List<CategoryAndQuantityDTO> results = categoryIds.stream().map(
                categoryId -> {
                    CategoryAndQuantityDTO categoryAndQuantityDTO = new CategoryAndQuantityDTO();
                    categoryAndQuantityDTO.setCategory(categoryMapper.mapTo(categoryService.findOne(categoryId).orElse(null)));
                    categoryAndQuantityDTO.setQuantity(0);
                    for (Object[] item : categoryAndQuantity) {
                        if (categoryId.equals(item[0])) {
                            categoryAndQuantityDTO.setQuantity(Integer.parseInt(item[1].toString()));
                            break;
                        }
                    }
                    return categoryAndQuantityDTO;
                }
        ).toList();

        return results;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createCategory(@RequestBody CategoryDTO categoryDTO) {
        if (!typePersonService.isExist(categoryDTO.getTypePersonId()))
            return new ResponseEntity<>(new ResponseMessage(false, "Type Person not found!"), HttpStatus.NOT_FOUND);

        Category category = categoryMapper.mapFrom(categoryDTO);
        Category createdCategory = categoryService.createAndUpdate(category);

        return new ResponseEntity<>(new ResponseMessage(true, "Category created successfully!"), HttpStatus.CREATED);
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
