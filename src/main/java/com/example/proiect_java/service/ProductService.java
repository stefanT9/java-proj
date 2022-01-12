package com.example.proiect_java.service;

import com.example.proiect_java.entity.CoffeeShop;
import com.example.proiect_java.entity.Product;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.example.proiect_java.repository.CoffeShopRepository;
import com.example.proiect_java.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CoffeShopRepository coffeShopRepository;

    @Autowired
    MongoOperations mongoOperations;

    public List<Product> getProducts(String coffeStoreId){
        try {
            Optional<CoffeeShop> coffeeShopOptional = coffeShopRepository.findById(coffeStoreId);
            return coffeeShopOptional.map(CoffeeShop::getProducts).orElse(null);
        }
        catch (Exception err){
            return null;
        }
    }

    public Product createProduct(String coffeStoreId, Product product){
        Optional<CoffeeShop> coffeeShopOptional = coffeShopRepository.findById(coffeStoreId);
        if(coffeeShopOptional.isPresent()){
            Product savedProduct = productRepository.save(product);
            CoffeeShop coffeeShop = coffeeShopOptional.get();

            List<Product> products = coffeeShop.getProducts();
            products.add(savedProduct);

            coffeShopRepository.save(coffeeShop);
            return savedProduct;
        } else {
            return null;
        }
    }

    public Product updateProduct(String coffeStoreId, String productId, Product product){
        Optional<CoffeeShop> coffeeShopOptional = coffeShopRepository.findById(coffeStoreId);
        if(coffeeShopOptional.isPresent()){
            Document document = new Document();
            mongoOperations.getConverter().write(product, document);
            Update update = new Update();
            document.forEach(update::set);

            return mongoOperations.findAndModify(
                    Query.query(Criteria.where("id").is(product)),
                    update,
                    Product.class);
        } else {
            return null;
        }
    }
    public Product removeProduct(String coffeStoreId, String productId){
        Optional<CoffeeShop> coffeeShopOptional = coffeShopRepository.findById(coffeStoreId);
        if(coffeeShopOptional.isPresent()){
            CoffeeShop coffeeShop = coffeeShopOptional.get();
            List<Product> products = coffeeShop.getProducts().stream().filter((product -> !product.getId().equals(productId))).collect(Collectors.toList());
            coffeeShop.setProducts(products);
            coffeShopRepository.save(coffeeShop);
            return productRepository.removeById(productId);
        } else {
            return null;
        }
    }
}
