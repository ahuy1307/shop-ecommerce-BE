package com.example.ecommerce.controllers;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.ColorAndQuantityDTO;
import com.example.ecommerce.models.DTO.ColorDTO;
import com.example.ecommerce.models.entities.Color;
import com.example.ecommerce.services.impl.ColorServiceImpl;
import com.example.ecommerce.services.impl.ProductColorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/color")
@RequiredArgsConstructor
public class ColorController {
    private final ColorServiceImpl colorService;
    private final Mapper<Color, ColorDTO> colorMapper;
    private final ProductColorServiceImpl productColorService;

    @GetMapping("/{id}")
    public ResponseEntity<ColorDTO> getOneColor(@PathVariable Integer id) {
        Color color = colorService.findOne(id).orElse(null);

        if (color == null)
            return ResponseEntity.notFound().build();
        return new ResponseEntity<>(colorMapper.mapTo(color), HttpStatus.OK);
    }

    @GetMapping
    public List<ColorDTO> getAllColor() {
        List<Color> colors = colorService.findAll();

        return colors.stream().map(colorMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<ColorDTO> getColorByName(@RequestParam String name) {
        List<Color> colors = colorService.searchColorByName(name);

        return colors.stream().map(colorMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping("/person/{typePersonId}")
    public List<ColorAndQuantityDTO> yourControllerMethod(@PathVariable Integer typePersonId,
                                                          @RequestParam(name = "size", required = false) List<Integer> listSizeId,
                                                          @RequestParam(name = "category", required = false) Integer categoryId) {
        // Gọi phương thức từ repository để lấy dữ liệu từ cơ sở dữ liệu
        List<Object[]> colorIdAndQuantity = productColorService.findColorCountsByCriteria(typePersonId, listSizeId, categoryId);
        List<Integer> colorIds = productColorService.findAllColorByTypePersonId(typePersonId);

        List<ColorAndQuantityDTO> results = new ArrayList<>();
        for (Integer colorId : colorIds) {
            ColorAndQuantityDTO colorAndQuantityDTO = new ColorAndQuantityDTO();
            colorAndQuantityDTO.setColor(colorMapper.mapTo(colorService.findOne(colorId).orElse(null)));
            colorAndQuantityDTO.setQuantity(0);
            for (Object[] item : colorIdAndQuantity) {
                if (colorId.equals(item[0])) {
                    colorAndQuantityDTO.setQuantity(Integer.parseInt(item[1].toString()));
                    break;
                }
            }
            results.add(colorAndQuantityDTO);
        }
        return results;
    }

    @PostMapping
    public ResponseEntity<ColorDTO> createColor(@RequestBody ColorDTO colorDTO) {
        Color color = colorMapper.mapFrom(colorDTO);
        Color createdColor = colorService.createAndUpdate(color);

        return new ResponseEntity<>(colorMapper.mapTo(createdColor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColorDTO> updateColor(@PathVariable Integer id, @RequestBody ColorDTO colorDTO) {
        if (!colorService.isExist(id))
            return ResponseEntity.notFound().build();

        Color color = colorMapper.mapFrom(colorDTO);
        color.setId(id);
        Color updatedColor = colorService.createAndUpdate(color);

        return new ResponseEntity<>(colorMapper.mapTo(updatedColor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable Integer id) {
        if (!colorService.isExist(id))
            return ResponseEntity.notFound().build();

        colorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
