package com.example.ecommerce.services.impl;

import com.example.ecommerce.models.entities.TypePerson;
import com.example.ecommerce.repositories.TypePersonRepository;
import com.example.ecommerce.services.TypePersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypePersonServiceImpl implements TypePersonService {
    private final TypePersonRepository typePersonRepository;

    @Override
    public Optional<TypePerson> findBySlug(String slug) {
        return typePersonRepository.findBySlug(slug);
    }

    @Override
    public List<TypePerson> findAll() {
        return typePersonRepository.findAll();
    }

    @Override
    public TypePerson createAndUpdate(TypePerson typePerson) {
        return typePersonRepository.save(typePerson);
    }

    @Override
    public void delete(Integer id) {
        typePersonRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Integer id) {
        return typePersonRepository.existsById(id);
    }
}
