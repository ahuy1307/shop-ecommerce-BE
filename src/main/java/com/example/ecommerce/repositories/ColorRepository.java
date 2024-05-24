package com.example.ecommerce.repositories;

import com.example.ecommerce.models.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

    @Query("SELECT c FROM Color c WHERE c.name like :name%")
    List<Color> getColorByName(String name);
}
