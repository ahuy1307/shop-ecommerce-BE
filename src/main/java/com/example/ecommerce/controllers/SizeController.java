package com.example.ecommerce.controllers;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.SizeAndQuantityDTO;
import com.example.ecommerce.models.DTO.SizeDTO;
import com.example.ecommerce.models.entities.Size;
import com.example.ecommerce.services.impl.ProductSizeServiceImpl;
import com.example.ecommerce.services.impl.SizeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/size")
@RequiredArgsConstructor
public class SizeController {
    private final SizeServiceImpl sizeService;
    private final Mapper<Size, SizeDTO> sizeMapper;
    private final ProductSizeServiceImpl productSizeService;

    @GetMapping
    public List<SizeDTO> getAllSize() {
        List<Size> sizes = sizeService.findAll();

        return sizes.stream().map(sizeMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SizeDTO> getOneSize(@PathVariable Integer id) {
        Size size = sizeService.findOne(id).orElse(null);

        if (size == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sizeMapper.mapTo(size));
    }

    @GetMapping("/person/{typePersonId}")
    public List<SizeAndQuantityDTO> yourControllerMethod(@PathVariable Integer typePersonId,
                                                         @RequestParam(name = "color", required = false) List<Integer> listColorId,
                                                         @RequestParam(name = "category", required = false) Integer categoryId) {
        List<Object[]> sizeAndQuantity = productSizeService.findSizeCountsByCriteria(typePersonId, listColorId, categoryId);
        List<Integer> sizeIds = productSizeService.findAllSizeIdByTypePersonId(typePersonId);

        List<SizeAndQuantityDTO> results = new ArrayList<>();
        for (Integer sizeId : sizeIds) {
            SizeAndQuantityDTO sizeIdAndQuantityDTO = new SizeAndQuantityDTO();
            sizeIdAndQuantityDTO.setSize(sizeMapper.mapTo(sizeService.findOne(sizeId).orElse(null)));
            sizeIdAndQuantityDTO.setQuantity(0);
            for (Object[] item : sizeAndQuantity) {
                if (sizeId.equals(item[0])) {
                    sizeIdAndQuantityDTO.setQuantity(Integer.parseInt(item[1].toString()));
                    break;
                }
            }
            results.add(sizeIdAndQuantityDTO);
        }
        return results;
    }

    @PostMapping
    public ResponseEntity<SizeDTO> createSize(@RequestBody SizeDTO sizeDTO) {
        Size size = sizeMapper.mapFrom(sizeDTO);
        Size createdSize = sizeService.createAndUpdate(size);

        return ResponseEntity.ok(sizeMapper.mapTo(createdSize));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SizeDTO> updateSize(@PathVariable Integer id, @RequestBody SizeDTO sizeDTO) {
        if (!sizeService.isExist(id))
            return ResponseEntity.notFound().build();

        Size size = sizeMapper.mapFrom(sizeDTO);
        size.setId(id);
        Size updatedSize = sizeService.createAndUpdate(size);

        return ResponseEntity.ok(sizeMapper.mapTo(updatedSize));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSize(@PathVariable Integer id) {
        if (!sizeService.isExist(id))
            return ResponseEntity.notFound().build();

        sizeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
