package com.example.ecommerce.repositories;

import com.example.ecommerce.models.entities.TypePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePersonRepository extends JpaRepository<TypePerson, Integer> {
}
