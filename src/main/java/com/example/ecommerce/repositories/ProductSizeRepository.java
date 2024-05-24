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

    @Query("SELECT distinct p.id.productId from ProductSize p where p.id.sizeId in :listSizeId")
    List<Integer> findAllByListSizeId(List<Integer> listSizeId);
}
