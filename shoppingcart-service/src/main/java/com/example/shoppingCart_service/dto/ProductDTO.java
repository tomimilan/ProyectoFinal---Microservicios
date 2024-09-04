package com.example.shoppingCart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long idProduct;
    private String name;
    private String brand;
    private double unitPrice;
}
