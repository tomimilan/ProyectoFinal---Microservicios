package com.example.shoppingCart_service.repository;

import com.example.shoppingCart_service.model.Shoppingcart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShoppingcartRepository extends JpaRepository<Shoppingcart, Long> {
}
