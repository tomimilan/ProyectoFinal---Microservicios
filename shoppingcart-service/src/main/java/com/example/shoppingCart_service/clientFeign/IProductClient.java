package com.example.shoppingCart_service.clientFeign;

import com.example.shoppingCart_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface IProductClient {

    @GetMapping("/productos/obtener/{idProduct}")
    public ProductDTO getProductById(@PathVariable ("idProduct") Long idProduct);

}
