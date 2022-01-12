package com.example.proiect_java.repository;

import com.example.proiect_java.entity.CoffeeShop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeShopRepository extends MongoRepository<CoffeeShop, String> {
    public List<CoffeeShop> findAll();
    public CoffeeShop removeById(String id);

}
