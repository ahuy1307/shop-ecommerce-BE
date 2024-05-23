package com.example.ecommerce.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    private Integer id;
    private String namePerson;
    private String phone;
    private String province;
    private String district;
    private String ward;
    private String currentAddress;
    private boolean isDefault;
    private String userId;
}
