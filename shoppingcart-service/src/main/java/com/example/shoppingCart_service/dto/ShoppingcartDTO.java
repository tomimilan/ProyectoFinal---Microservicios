package com.example.shoppingCart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingcartDTO {

    private Long idShoppingcart;
    private double totalPrice;
    private List<String> productNames;
}
