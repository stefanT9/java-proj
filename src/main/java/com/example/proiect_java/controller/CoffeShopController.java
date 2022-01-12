package com.example.proiect_java.controller;

import com.example.proiect_java.entity.CoffeeShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.proiect_java.service.CoffeShopService;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class CoffeShopController {

    @Autowired
    CoffeShopService coffeShopService;

    @GetMapping()
    public List<CoffeeShop> getAllCoffeShops(){
        return coffeShopService.findAllCoffeShops();
    }

    @PostMapping()
    public CoffeeShop postCoffeShop(@RequestBody CoffeeShop coffeeShop){
        return coffeShopService.createCoffeShop(coffeeShop);
    }

    @PutMapping("/{id}")
    public CoffeeShop postCoffeShop(@PathVariable String id,@RequestBody CoffeeShop coffeeShop){
        return coffeShopService.editCoffeShop(id, coffeeShop);
    }

    @DeleteMapping("/{id}")
    public CoffeeShop removeCoffeShop(@PathVariable String id){
        return coffeShopService.deleteCoffeShop(id);
    }
}
