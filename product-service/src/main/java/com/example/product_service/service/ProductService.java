package com.example.product_service.service;

import com.example.product_service.model.Product;
import com.example.product_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepo;

    //CRUD
    @Override
    public Product getProductById(Long idProduct) {
        Product product = productRepo.findById(idProduct).orElse(null);
        return product;
    }
    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepo.findAll();
        return products;
    }
    @Override
    public void saveProduct(Product product) {
        productRepo.save(product);
    }



    @Override
    public void deleteProductById(Long idProduct) {
        Product product = this.getProductById(idProduct);
    }

    @Override
    public void editProduct(Long idProduct, Product productoEditar) {
        Product product = this.getProductById(idProduct);

        product.setName(productoEditar.getName());
        product.setBrand(productoEditar.getBrand());
        product.setUnitPrice(productoEditar.getUnitPrice());

        this.saveProduct(product);
    }

}
