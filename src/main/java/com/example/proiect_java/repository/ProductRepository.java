package com.example.proiect_java.repository;

import com.example.proiect_java.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    public List<Product> findAll();
    public Product removeById(String id);
}
