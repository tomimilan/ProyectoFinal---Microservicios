package com.example.sales_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private Long idSale;
    private LocalDate saleDate;
    private Long idShoppingcart;
    private double totalPrice;
    private List<String> products;
}
