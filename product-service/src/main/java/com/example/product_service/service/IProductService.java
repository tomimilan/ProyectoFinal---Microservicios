package com.example.product_service.service;

import com.example.product_service.model.Product;

import java.util.List;

public interface IProductService {
    public Product getProductById(Long idProduct);
    public List<Product> getProducts();
    public void saveProduct(Product product);
    public void deleteProductById(Long idProduct);
    public void editProduct(Long idProduct, Product productoEditar);

}
