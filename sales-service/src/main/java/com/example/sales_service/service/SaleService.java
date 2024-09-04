package com.example.sales_service.service;

import com.example.sales_service.clientFeign.IShoppingcartClient;
import com.example.sales_service.dto.SaleDTO;
import com.example.sales_service.dto.ShoppingcartDTO;
import com.example.sales_service.model.Sale;
import com.example.sales_service.repository.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService{

    @Autowired
    private ISaleRepository saleRepo;
    @Autowired
    private IShoppingcartClient shoppClient;


    @Override
    public void saveSale(Sale sale) {

        saleRepo.save(sale);
    }
    @Override
    @CircuitBreaker(name = "shoppingCart-service", fallbackMethod = "fallbackGetSaleById")
    @Retry(name = "shoppingCart-service")
    public SaleDTO getSaleById(Long idSale) {
        Sale sale = saleRepo.findById(idSale).orElse(null);
        ShoppingcartDTO shoppingcartDTO = shoppClient.getShoppingcart(sale.getIdShoppingcart());
        SaleDTO saleDTO = new SaleDTO(sale.getIdSale(), sale.getSaleDate(), sale.getIdShoppingcart(), shoppingcartDTO.getTotalPrice(), shoppingcartDTO.getProductNames());
        return saleDTO;
    }
    @Override
    public List<Sale> getSales() {
        List<Sale> sales = saleRepo.findAll();
        return sales;
    }
    @Override
    public void deleteSaleById(Long idSale) {
        saleRepo.deleteById(idSale);
    }

    //FallBackMethod
    public SaleDTO fallbackGetSaleById(Throwable throwable){
        return new SaleDTO(999999L, null, 999999L, 0000000, null);
    }
}
