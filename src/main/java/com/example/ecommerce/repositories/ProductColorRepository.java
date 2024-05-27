package com.example.ecommerce.repositories;

import com.example.ecommerce.models.embed.ProductColorEmbed;
import com.example.ecommerce.models.entities.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, ProductColorEmbed> {

    List<ProductColor> findAllById_ProductId(Integer productId);

    @Query("SELECT p.id.colorId, COUNT(DISTINCT pr.id) AS productCount " +
            "FROM ProductColor p " +
            "JOIN Product pr ON p.id.productId = pr.id " +
            "JOIN ProductSize ps ON pr.id = ps.id.productId " +
            "JOIN Category c ON pr.categoryId = c.id " +
            "JOIN TypePerson t ON c.typePersonId = t.id " +
            "WHERE t.id = :typePersonId " +
            "AND (ps.id.sizeId IN :listSizeId OR :listSizeId IS NULL) " +
            "AND (c.id = :categoryId OR :categoryId IS NULL) " +
            "GROUP BY p.id.colorId " +
            "ORDER BY p.id.colorId ASC")
    List<Object[]> findColorCountsByCriteria(Integer typePersonId,
                                             List<Integer> listSizeId,
                                             Integer categoryId);

    @Query("SELECT distinct p.id.colorId from ProductColor p " +
            "JOIN Product pr on p.id.productId = pr.id " +
            "JOIN Category c on pr.categoryId = c.id " +
            "JOIN TypePerson t on c.typePersonId = t.id " +
            "where t.id = :typePersonId order by p.id.colorId asc")
    List<Integer> findAllColorIdByTypePersonId(Integer typePersonId);
}
