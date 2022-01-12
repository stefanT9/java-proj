package com.example.proiect_java.controller;

import com.example.proiect_java.entity.CardTemplate;
import com.example.proiect_java.service.CardTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffeShop/{coffeShopId}/cardTemplate")
public class CardTemplateController {

    @Autowired
    CardTemplateService cardTemplateService;

    @GetMapping()
    public List<CardTemplate> getCardTemplateFromCoffeShop(@PathVariable String coffeShopId){
        return cardTemplateService.getCardTemplatesFromCoffeShop(coffeShopId);
    }

    @PostMapping()
    public CardTemplate addCardTemplateToCoffeShop(@PathVariable String coffeShopId, @RequestBody CardTemplate cardTemplate){
        return cardTemplateService.addCardTemplateToCoffeShop(coffeShopId, cardTemplate);
    }

    @PutMapping("/{cardTemplateId}")
    public CardTemplate editCardTemplate(@PathVariable String coffeShopId, @RequestBody CardTemplate cardTemplate, @PathVariable String cardTemplateId){
        return cardTemplateService.editCardTemplate(cardTemplateId, cardTemplate);
    }

    @DeleteMapping("/{cardTemplateId}")
    public CardTemplate deleteCardTemplate(@PathVariable String coffeShopId, @PathVariable String cardTemplateId){
        return cardTemplateService.deleteCardTemplate(coffeShopId, cardTemplateId);
    }
}
