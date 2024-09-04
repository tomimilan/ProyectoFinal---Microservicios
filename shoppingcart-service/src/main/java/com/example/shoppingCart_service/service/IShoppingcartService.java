package com.example.shoppingCart_service.service;

import com.example.shoppingCart_service.dto.ShoppingcartDTO;
import com.example.shoppingCart_service.model.Shoppingcart;

import java.util.List;

public interface IShoppingcartService {

    //OTHER
    public void addProduct(Long idShoppingcart, Long idProduct);
    public void deleteProduct(Long idShoppingcart, Long idProduct);
    public ShoppingcartDTO getInfoShoppingcart(Long idShoppingcart);

    //CRUD
    public void saveShoppingcart(Shoppingcart shoppingcart);
    public Shoppingcart getShoppingcartById(Long idShoppingcart);
    public List<Shoppingcart> getShoppingcarts();
}
