package com.example.ecommerce.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private float price;
    private String composition;
    private String neckline;
    @Column(name = "sleeve_length")
    private String sleeveLength;
    @Column(name = "care_instruction")
    private String careInstruction;
    private String slug;
    @Column(name = "category_id")
    private Integer categoryId;
}
