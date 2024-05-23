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
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_person")
    private String namePerson;
    private String phone;
    private String province;
    private String district;
    private String ward;

    @Column(name = "current_address")
    private String currentAddress;

    @Column(name = "is_default")
    private boolean isDefault;

    @Column(name = "user_id")
    private String userId;
}
