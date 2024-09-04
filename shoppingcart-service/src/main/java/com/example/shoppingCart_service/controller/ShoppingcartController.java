package com.example.shoppingCart_service.controller;

import com.example.shoppingCart_service.dto.ShoppingcartDTO;
import com.example.shoppingCart_service.model.Shoppingcart;
import com.example.shoppingCart_service.service.IShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingcartController {

    @Autowired
    private IShoppingcartService shoppingcartServ;

    //OTHER
    @PostMapping("/carrito/agregarproducto/{idShoppingcart}/{idProduct}")
    public String addProduct(@PathVariable Long idShoppingcart, @PathVariable Long idProduct){
        shoppingcartServ.addProduct(idShoppingcart, idProduct);
        return "Producto agregado a carrito";
    }
    @DeleteMapping("/carrito/eliminarproducto/{idShoppingcart}/{idProduct}")
    public String deleteProduct(@PathVariable Long idShoppingcart, @PathVariable Long idProduct){
        shoppingcartServ.deleteProduct(idShoppingcart, idProduct);
        return "Producto eliminado de carrito";
    }
    @GetMapping("/carrito/info/{idShoppingcart}")
    public ShoppingcartDTO getInfoShoppingcart(@PathVariable Long idShoppingcart){
        return shoppingcartServ.getInfoShoppingcart(idShoppingcart);
    }





    //CRUD
    @GetMapping("/carrito/obtener/{idShoppingcart}")
    public Shoppingcart getShoppingcartById(@PathVariable Long idShoppingcart){
        return shoppingcartServ.getShoppingcartById(idShoppingcart);
    }
    @PostMapping("/carrito/guardar")
    public String saveShoppingcart(@RequestBody Shoppingcart shoppingcart){
        shoppingcartServ.saveShoppingcart(shoppingcart);
        return "Carrito creado exitosamente";
    }
    @GetMapping("/carrito/obtener")
    public List<Shoppingcart> getShoppingcarts(){
        return shoppingcartServ.getShoppingcarts();
    }

}
