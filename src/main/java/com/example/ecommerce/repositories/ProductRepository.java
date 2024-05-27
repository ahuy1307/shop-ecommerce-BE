package com.example.ecommerce.repositories;

import com.example.ecommerce.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findBySlug(String slug);

    @Query("SELECT p.id " +
            "FROM Product p " +
            "JOIN ProductColor pc ON p.id = pc.id.productId " +
            "JOIN ProductSize ps ON p.id = ps.id.productId " +
            "JOIN Category c ON p.categoryId = c.id " +
            "JOIN TypePerson t ON c.typePersonId = t.id " +
            "WHERE t.id = :typePersonId " +
            "AND c.id = :categoryId or :categoryId is NULL " +
            "AND (pc.id.colorId IN :listColorId OR :listColorId IS NULL) " +
            "AND (ps.id.sizeId IN :listSizeId OR :listSizeId IS NULL) " +
            "GROUP BY p.id " +
            "ORDER BY p.id ASC")
    List<Integer> findProductByCriteria(Integer typePersonId,
                                        Integer categoryId,
                                        List<Integer> listColorId,
                                        List<Integer> listSizeId);
}
