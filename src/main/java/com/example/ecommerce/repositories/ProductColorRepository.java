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

    @Query("SELECT distinct p.id.colorId FROM ProductColor p " +
            "JOIN Product pr on p.id.productId = pr.id " +
            "JOIN Category c on pr.categoryId = c.id " +
            "join TypePerson t on c.typePersonId = t.id " +
            "where t.id = :typePersonId order by p.id.colorId asc")
    List<Integer> findAllByTypePersonId(Integer typePersonId);

    @Query("SELECT distinct p.id.productId from ProductColor p where p.id.colorId in :listColorId")
    List<Integer> findAllByListColorId(List<Integer> listColorId);
}
