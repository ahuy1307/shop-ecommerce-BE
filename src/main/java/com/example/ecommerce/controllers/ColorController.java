package com.example.ecommerce.controllers;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.ColorDTO;
import com.example.ecommerce.models.entities.Color;
import com.example.ecommerce.services.impl.ColorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/color")
@RequiredArgsConstructor
public class ColorController {
    private final ColorServiceImpl colorService;
    private final Mapper<Color, ColorDTO> colorMapper;

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
        List<Color> colors = colorService.getColorByName(name);

        return colors.stream().map(colorMapper::mapTo).collect(Collectors.toList());
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
