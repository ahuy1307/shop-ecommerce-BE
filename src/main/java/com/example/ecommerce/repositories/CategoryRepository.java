package com.example.ecommerce.repositories;

import com.example.ecommerce.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoryByTypePersonId(Integer typePersonId);

    @Query("SELECT c.id, COUNT(DISTINCT pr.id) AS productCount " +
            "FROM Category c " +
            "JOIN Product pr ON c.id = pr.categoryId " +
            "JOIN ProductColor pc ON pr.id = pc.id.productId " +
            "JOIN ProductSize ps ON pr.id = ps.id.productId " +
            "JOIN TypePerson t ON c.typePersonId = t.id " +
            "WHERE t.id = :typePersonId " +
            "AND (pc.id.colorId IN :listColorId OR :listColorId IS NULL) " +
            "AND (ps.id.sizeId IN :listSizeId OR :listSizeId IS NULL) " +
            "GROUP BY c.id " +
            "ORDER BY c.id ASC")
    List<Object[]> findCategoryCountByCriteria(Integer typePersonId,
                                               List<Integer> listColorId,
                                               List<Integer> listSizeId);

    @Query("SELECT c.id FROM Category c WHERE c.typePersonId = :typePersonId order by c.id asc")
    List<Integer> findAllCategoryIdByTypePersonId(Integer typePersonId);
}
