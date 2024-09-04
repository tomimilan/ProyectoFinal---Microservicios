package com.example.sales_service.service;

import com.example.sales_service.dto.SaleDTO;
import com.example.sales_service.model.Sale;

import java.util.List;

public interface ISaleService {

    public void saveSale(Sale sale);
    public SaleDTO getSaleById(Long idSale);
    public List<Sale> getSales();
    public void deleteSaleById(Long idSale);
}
