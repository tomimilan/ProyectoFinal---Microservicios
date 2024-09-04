package com.example.product_service.controller;

import com.example.product_service.model.Product;
import com.example.product_service.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private IProductService productServ;
    /* Para visualizar el uso de Cloud Load Balancer, vamos a obtener el puerto desde app.properties
    y guardarlo en la variable serverPort, para luego mediante la ejecución de 2 instancias de Product
    en diferentes puertos y Postman, simular varias solicitudes y por consola ver en cual puerto se recibió  */
    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/obtener/{idProduct}")
    public ResponseEntity<Product> getProductById(@PathVariable Long idProduct){
        Product product = productServ.getProductById(idProduct);
        if (product != null){
            System.out.println("---------------ESTOY EN EL PUERTO: "+serverPort);
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/guardar")
    public ResponseEntity<String> saveProduct(@RequestBody Product product){
        productServ.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado exitosamente");
    }
    @GetMapping("/obtener")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productServ.getProducts());
    }
    @DeleteMapping("/eliminar/{idProduct}")
    public String deleteProductById(@PathVariable Long idProduct){

        Product product = productServ.getProductById(idProduct);
        if (product != null) {
            productServ.deleteProductById(idProduct);
            return "Producto eliminado correctamente";
        } else {
            return "No se encuentra producto con id: "+idProduct;
        }

    }
    @PutMapping("/editar/{idProduct}")
    public ResponseEntity<String> editProduct(@PathVariable Long idProduct, @RequestBody Product productoEditar){
        productServ.editProduct(idProduct, productoEditar);
        return ResponseEntity.ok("Producto editado correctamente");
    }
}
