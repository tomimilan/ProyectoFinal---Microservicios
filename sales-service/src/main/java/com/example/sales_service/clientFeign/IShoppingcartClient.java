package com.example.sales_service.clientFeign;

import com.example.sales_service.dto.ShoppingcartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shoppingCart-service")
public interface IShoppingcartClient {

    @GetMapping("/carrito/info/{idShoppingcart}")
    public ShoppingcartDTO getShoppingcart(@PathVariable ("idShoppingcart") Long idShoppingcart);
}
