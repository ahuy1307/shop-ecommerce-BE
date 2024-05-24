package com.example.ecommerce.repositories;

import com.example.ecommerce.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategoryId(Integer categoryId);

    @Query("SELECT p FROM Product p " +
            "JOIN Category c on p.categoryId = c.id " +
            "join TypePerson t on c.typePersonId = t.id " +
            "where t.id = :typePersonId")
    List<Product> findAllByTypePersonId(Integer typePersonId);
}
