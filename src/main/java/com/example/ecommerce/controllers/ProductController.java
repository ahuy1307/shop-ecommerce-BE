package com.example.ecommerce.controllers;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.ProductDTO;
import com.example.ecommerce.models.DTO.ResponseMessage;
import com.example.ecommerce.models.embed.ProductColorEmbed;
import com.example.ecommerce.models.embed.ProductSizeEmbed;
import com.example.ecommerce.models.entities.Product;
import com.example.ecommerce.models.entities.ProductColor;
import com.example.ecommerce.models.entities.ProductSize;
import com.example.ecommerce.services.impl.CategoryServiceImpl;
import com.example.ecommerce.services.impl.ProductColorServiceImpl;
import com.example.ecommerce.services.impl.ProductServiceImpl;
import com.example.ecommerce.services.impl.ProductSizeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;
    private final Mapper<Product, ProductDTO> productMapper;
    private final CategoryServiceImpl categoryService;
    private final ProductSizeServiceImpl productSizeService;
    private final ProductColorServiceImpl productColorService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getOneProduct(@PathVariable Integer id) {
        Product product = productService.findOne(id).orElse(null);

        if (product == null)
            return ResponseEntity.notFound().build();

        ProductDTO productDTO = productMapper.mapTo(product);
        productDTO.setColors(productColorService.getColorByProductId(id));
        productDTO.setSizes(productSizeService.getSizeByProductId(id));

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    private List<ProductDTO> getListProductDTO(List<Product> products) {
        List<ProductDTO> productDTOs = products.stream().map(
                item -> {
                    ProductDTO productDTO = productMapper.mapTo(item);
                    productDTO.setColors(productColorService.getColorByProductId(item.getId()));
                    productDTO.setSizes(productSizeService.getSizeByProductId(item.getId()));
                    return productDTO;
                }
        ).toList();

        return productDTOs;
    }

    @GetMapping
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productService.findAll();

        return getListProductDTO(products);
    }


    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getAllProductByCategoryId(@PathVariable Integer categoryId) {
        List<Product> products = productService.findAllByCategoryId(categoryId);

        return getListProductDTO(products);
    }

    @GetMapping("/person/{typePersonId}")
    public List<ProductDTO> getAllProductByTypePersonId(@PathVariable Integer typePersonId) {
        List<Product> products = productService.findAllByTypePersonId(typePersonId);

        return getListProductDTO(products);
    }

    private void createAndUpdateColorAndSize(ProductDTO productDTO, Product createdProduct) {
        productDTO.getColors().stream().map(item -> {
            ProductColor productColor = ProductColor.builder()
                    .id(ProductColorEmbed.builder()
                            .productId(createdProduct.getId())
                            .colorId(item.getColor().getId())
                            .build())
                    .thumbnail(item.getThumbnail())
                    .build();
            return productColorService.createAndUpdate(productColor);
        }).toList();

        productDTO.getSizes().stream().map(item -> {
            ProductSize productSize = ProductSize.builder()
                    .id(ProductSizeEmbed.builder()
                            .productId(createdProduct.getId())
                            .sizeId(item.getSize().getId())
                            .build())
                    .quantityStock(item.getQuantityStock())
                    .build();
            return productSizeService.createAndUpdate(productSize);
        }).toList();
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createProduct(@RequestBody ProductDTO productDTO) {
        if (!categoryService.isExist(productDTO.getCategoryId()))
            return new ResponseEntity<>(new ResponseMessage(false, "Category not found!"), HttpStatus.NOT_FOUND);

        Product product = productMapper.mapFrom(productDTO);
        Product createdProduct = productService.createAndUpdate(product);

        createAndUpdateColorAndSize(productDTO, createdProduct);

        return new ResponseEntity<>(new ResponseMessage(true, "Product created successfully!"), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        if (!productService.isExist(id))
            return ResponseEntity.notFound().build();

        Product product = productMapper.mapFrom(productDTO);
        product.setId(id);

        Product updatedProduct = productService.createAndUpdate(product);
        List<ProductColor> productColors = productColorService.findAllById_ProductId(id);

        List<Integer> colorIds = productDTO.getColors().stream().map(item -> item.getColor().getId()).toList();

        productColors.stream().forEach(item -> {
            if (!colorIds.contains(item.getId().getColorId()))
                productColorService.delete(item.getId());
        });

        createAndUpdateColorAndSize(productDTO, updatedProduct);

        ProductDTO updatedProductDTO = productMapper.mapTo(updatedProduct);
        updatedProductDTO.setColors(productColorService.getColorByProductId(id));
        updatedProductDTO.setSizes(productSizeService.getSizeByProductId(id));

        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        if (!productService.isExist(id))
            return ResponseEntity.notFound().build();
        
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
