package com.example.shoppingCart_service.service;

import com.example.shoppingCart_service.dto.ProductDTO;
import com.example.shoppingCart_service.dto.ShoppingcartDTO;
import com.example.shoppingCart_service.model.Shoppingcart;
import com.example.shoppingCart_service.clientFeign.IProductClient;
import com.example.shoppingCart_service.repository.IShoppingcartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingcartService implements IShoppingcartService{

    @Autowired
    private IShoppingcartRepository shoppingcartRepo;
    @Autowired
    private IProductClient productClient;

    //CRUD
    @Override
    public void saveShoppingcart(Shoppingcart shoppingcart) {
        shoppingcartRepo.save(shoppingcart);
    }
    @Override
    public Shoppingcart getShoppingcartById (Long idShoppingcart){
        Shoppingcart shoppingcart = shoppingcartRepo.findById(idShoppingcart).orElse(null);
        return shoppingcart;
    }
    @Override
    public List<Shoppingcart> getShoppingcarts() {
        List<Shoppingcart> shoppingcarts = shoppingcartRepo.findAll();
        return shoppingcarts;
    }

    //OTHERS
    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "fallbackAddProduct")
    @Retry(name = "product-service")
    public void addProduct(Long idShoppingcart, Long idProduct){
        Shoppingcart shoppingcart = this.getShoppingcartById(idShoppingcart);
        ProductDTO productDTO = productClient.getProductById(idProduct);
        shoppingcart.getProducts().add(idProduct);
        boolean i = true;
        updatePrice(shoppingcart, productDTO, i);
    }
    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "fallbackDeleteProduct")
    @Retry(name = "product-service")
    public void deleteProduct(Long idShoppingcart, Long idProduct) {
        Shoppingcart shoppingcart = this.getShoppingcartById(idShoppingcart);
        ProductDTO productDTO = productClient.getProductById(idProduct);
        shoppingcart.getProducts().remove(idProduct);
        boolean i = false;
        updatePrice(shoppingcart, productDTO, i);
    }
    public void updatePrice(Shoppingcart shoppingcart, ProductDTO productDTO, boolean i){
        if (i == true){
            double price = shoppingcart.getTotalPrice() + productDTO.getUnitPrice();
            shoppingcart.setTotalPrice(price);
            this.saveShoppingcart(shoppingcart);
        } else {
            double price = shoppingcart.getTotalPrice() - productDTO.getUnitPrice();
            shoppingcart.setTotalPrice(price);
            this.saveShoppingcart(shoppingcart);
        }

    }
    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "fallbackGetInfoShoppingcart")
    @Retry(name = "product-service")
    public ShoppingcartDTO getInfoShoppingcart (Long idShoppingcart) {
        //Busco en repositorio la instancia del carrito
        Shoppingcart shoppingcart = this.getShoppingcartById(idShoppingcart);
        //Creo DTO que va a guardar los nombres de los productos, ya que tengo Id y no nombres.
        ShoppingcartDTO shoppingcartDTO = new ShoppingcartDTO();
        //Lista con los ids para recorrerla y por cada id consumir el nombre de producto.
        List<Long> productsAux = shoppingcart.getProducts();
        List<String> namesAux = new ArrayList<>();

        shoppingcartDTO.setIdShoppingcart(shoppingcart.getIdShoppingcart());
        shoppingcartDTO.setTotalPrice(shoppingcart.getTotalPrice());
        //Recorro la lista de id, y por cada id consumo el servicio de productos
        //Luego agrego el nombre de productos a la nueva lista
        for (Long idProduct:productsAux){
            ProductDTO productDTO = productClient.getProductById(idProduct);
            namesAux.add(productDTO.getName());
        }
        shoppingcartDTO.setProductNames(namesAux);
        return shoppingcartDTO;
    }

    //FallBackMethod
    public void fallbackAddProduct(Throwable throwable){
        System.out.println("Circuit breaker: Error al agregar un producto. Intente nuevamente");
    }
    public void fallbackDeleteProduct(Throwable throwable){
        System.out.println("Circuit breaker: Error al eliminar un producto. Intente nuevamente");
    }
    public ShoppingcartDTO fallbackGetInfoShoppingcart(Throwable throwable){
        return new ShoppingcartDTO(999999L, 0000000, null);
    }





}
