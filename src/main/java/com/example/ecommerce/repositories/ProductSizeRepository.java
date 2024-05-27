package com.example.ecommerce.repositories;

import com.example.ecommerce.models.embed.ProductSizeEmbed;
import com.example.ecommerce.models.entities.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, ProductSizeEmbed> {

    List<ProductSize> findAllById_ProductId(Integer productId);

    @Query("SELECT distinct p.id.sizeId FROM ProductSize p " +
            "JOIN Product pr on p.id.productId = pr.id " +
            "JOIN Category c on pr.categoryId = c.id " +
            "join TypePerson t on c.typePersonId = t.id " +
            "where t.id = :typePersonId order by p.id.sizeId asc")
    List<Integer> findAllByTypePersonId(Integer typePersonId);

    @Query("SELECT p.id.sizeId, COUNT(DISTINCT pr.id) AS productCount " +
            "FROM ProductSize p " +
            "JOIN Product pr ON p.id.productId = pr.id " +
            "JOIN ProductColor pc ON pr.id = pc.id.productId " +
            "JOIN Category c ON pr.categoryId = c.id " +
            "JOIN TypePerson t ON c.typePersonId = t.id " +
            "WHERE t.id = :typePersonId " +
            "AND (pc.id.colorId IN :listColorId OR :listColorId IS NULL) " +
            "AND (c.id = :categoryId OR :categoryId IS NULL) " +
            "GROUP BY p.id.sizeId " +
            "ORDER BY p.id.sizeId ASC")
    List<Object[]> findSizeCountsByCriteria(Integer typePersonId,
                                            List<Integer> listColorId,
                                            Integer categoryId);

    @Query("SELECT distinct p.id.sizeId from ProductSize p " +
            "JOIN Product pr on p.id.productId = pr.id " +
            "JOIN Category c on pr.categoryId = c.id " +
            "JOIN TypePerson t on c.typePersonId = t.id " +
            "where t.id = :typePersonId order by p.id.sizeId asc")
    List<Integer> findAllSizeIdByTypePersonId(Integer typePersonId);
}
