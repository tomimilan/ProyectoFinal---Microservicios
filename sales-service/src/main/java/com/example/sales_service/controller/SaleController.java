package com.example.sales_service.controller;

import com.example.sales_service.dto.SaleDTO;
import com.example.sales_service.model.Sale;
import com.example.sales_service.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class SaleController {

    @Autowired
    private ISaleService saleServ;

    @PostMapping("/guardar")
    public String saveSale(@RequestBody Sale sale){
        saleServ.saveSale(sale);
        return "Venta creada exitosamente";
    }
    @GetMapping("/{idSale}")
    public SaleDTO getSaleById(@PathVariable ("idSale") Long idSale){
        return saleServ.getSaleById(idSale);
    }
    @GetMapping
    public List<Sale> getSales(){
        return saleServ.getSales();
    }
    @DeleteMapping("borrar/{idSale}")
    public String deleteSaleById(@PathVariable ("idSale") Long idSale){
        saleServ.deleteSaleById(idSale);
        return "Venta eliminada";
    }
}
