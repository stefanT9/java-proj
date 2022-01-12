package com.example.proiect_java.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.proiect_java.entity.CoffeeShop;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.example.proiect_java.repository.CoffeShopRepository;

import java.util.List;
import java.util.Map;

@Service
public class CoffeShopService {

    @Autowired
    CoffeShopRepository coffeShopRepository;
    @Autowired
    MongoOperations mongoOperations;

    public List<CoffeeShop> findAllCoffeShops(){
        return coffeShopRepository.findAll();
    }

    public CoffeeShop createCoffeShop(CoffeeShop coffeeShop){
        return coffeShopRepository.save(coffeeShop);
    }

    public CoffeeShop editCoffeShop(String id, CoffeeShop coffeeShop){
        Document document = new Document();
        mongoOperations.getConverter().write(coffeeShop, document);
        Update update = new Update();
        document.forEach(update::set);

        return mongoOperations.findAndModify(
                Query.query(Criteria.where("id").is(id)),
                update,
                CoffeeShop.class);
    }
    public CoffeeShop deleteCoffeShop(String id){
        return coffeShopRepository.removeById(id);
    }
}
