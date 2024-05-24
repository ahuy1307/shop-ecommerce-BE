package com.example.ecommerce.models.DTO;

public record ResponseMessage(
        boolean success,
        String message
) {
}
