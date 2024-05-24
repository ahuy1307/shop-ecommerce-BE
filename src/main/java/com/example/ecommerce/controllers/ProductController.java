package com.example.ecommerce.controllers;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.ProductDTO;
import com.example.ecommerce.models.DTO.ResponseMessage;
import com.example.ecommerce.models.entities.Product;
import com.example.ecommerce.services.impl.CategoryServiceImpl;
import com.example.ecommerce.services.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;
    private final Mapper<Product, ProductDTO> productMapper;
    private final CategoryServiceImpl categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getOneProduct(@PathVariable Integer id) {
        Product product = productService.findOne(id).orElse(null);

        if (product == null)
            return ResponseEntity.notFound().build();
        return new ResponseEntity<>(productMapper.mapTo(product), HttpStatus.OK);
    }

    @GetMapping
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productService.findAll();

        return products.stream().map(productMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getAllProductByCategoryId(@PathVariable Integer categoryId) {
        List<Product> products = productService.findAllByCategoryId(categoryId);

        return products.stream().map(productMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping("/person/{typePersonId}")
    public List<ProductDTO> getAllProductByTypePersonId(@PathVariable Integer typePersonId) {
        List<Product> products = productService.findAllByTypePersonId(typePersonId);

        return products.stream().map(productMapper::mapTo).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createProduct(@RequestBody ProductDTO productDTO) {
        if (!categoryService.isExist(productDTO.getCategoryId()))
            return new ResponseEntity<>(new ResponseMessage(false, "Category not found!"), HttpStatus.NOT_FOUND);

        Product product = productMapper.mapFrom(productDTO);
        Product createdProduct = productService.createAndUpdate(product);

        return new ResponseEntity<>(new ResponseMessage(true, "Product created successfully!"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        if (!productService.isExist(id))
            return ResponseEntity.notFound().build();

        Product product = productMapper.mapFrom(productDTO);
        product.setId(id);
        Product updatedProduct = productService.createAndUpdate(product);

        return new ResponseEntity<>(productMapper.mapTo(updatedProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
