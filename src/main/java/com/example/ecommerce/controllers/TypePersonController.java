package com.example.ecommerce.controllers;

import com.example.ecommerce.mappers.Mapper;
import com.example.ecommerce.models.DTO.TypePersonDTO;
import com.example.ecommerce.models.entities.TypePerson;
import com.example.ecommerce.services.impl.TypePersonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/person")
@RequiredArgsConstructor
public class TypePersonController {
    private final Mapper<TypePerson, TypePersonDTO> typePersonMapper;
    private final TypePersonServiceImpl typePersonService;

    @GetMapping("/{id}")
    public ResponseEntity<TypePersonDTO> getOneTypePerson(@PathVariable Integer id) {
        TypePerson typePerson = typePersonService.findOne(id).orElse(null);

        if (typePerson == null)
            return ResponseEntity.notFound().build();
        return new ResponseEntity<>(typePersonMapper.mapTo(typePerson), HttpStatus.OK);
    }

    @GetMapping
    public List<TypePersonDTO> getAllTypePerson() {
        List<TypePerson> typePerson = typePersonService.findAll();

        return typePerson.stream().map(typePersonMapper::mapTo).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<TypePersonDTO> createTypePerson(@RequestBody TypePersonDTO typePersonDTO) {
        TypePerson typePerson = typePersonMapper.mapFrom(typePersonDTO);
        TypePerson createdTypePerson = typePersonService.createAndUpdate(typePerson);

        return new ResponseEntity<>(typePersonMapper.mapTo(createdTypePerson), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypePersonDTO> updateTypePerson(@PathVariable Integer id,
                                                          @RequestBody TypePersonDTO typePersonDTO) {
        if (!typePersonService.isExist(id))
            return ResponseEntity.notFound().build();

        TypePerson typePerson = typePersonMapper.mapFrom(typePersonDTO);
        typePerson.setId(id);
        TypePerson updatedTypePerson = typePersonService.createAndUpdate(typePerson);

        return new ResponseEntity<>(typePersonMapper.mapTo(updatedTypePerson), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypePerson(@PathVariable Integer id) {
        if (!typePersonService.isExist(id))
            return ResponseEntity.notFound().build();

        typePersonService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
