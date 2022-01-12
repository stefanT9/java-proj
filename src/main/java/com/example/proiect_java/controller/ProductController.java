package com.example.proiect_java.controller;

import com.example.proiect_java.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.proiect_java.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/shop/{coffeStoreId}/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public List<Product> getProducts(@PathVariable String coffeStoreId){
        return productService.getProducts(coffeStoreId);
    }

    @PostMapping()
    public Product postProduct(@RequestBody Product product, @PathVariable String coffeStoreId){
        return productService.createProduct(coffeStoreId, product);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@RequestBody Product product, @PathVariable String coffeStoreId, @PathVariable String productId){
        return productService.updateProduct(coffeStoreId, productId, product);
    }

    @DeleteMapping("/{productId}")
    public Product removeProduct(@PathVariable String coffeStoreId, @PathVariable String productId){
        return productService.removeProduct(coffeStoreId, productId);
    }
}
