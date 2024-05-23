package com.example.ecommerce.services;

import com.example.ecommerce.models.entities.TypePerson;

import java.util.List;
import java.util.Optional;

public interface TypePersonService {
    Optional<TypePerson> findOne(Integer id);

    List<TypePerson> findAll();
    
    TypePerson createAndUpdate(TypePerson typePerson);

    void delete(Integer id);

    boolean isExist(Integer id);
}
