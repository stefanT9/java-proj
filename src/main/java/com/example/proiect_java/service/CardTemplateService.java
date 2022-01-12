package com.example.proiect_java.service;

import com.example.proiect_java.entity.Card;
import com.example.proiect_java.entity.CardTemplate;
import com.example.proiect_java.entity.CoffeeShop;
import com.example.proiect_java.repository.CardTemplateRepository;
import com.example.proiect_java.repository.CoffeShopRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardTemplateService {
    @Autowired
    MongoOperations mongoOperations;
    @Autowired
    CardTemplateRepository cardTemplateRepository;
    @Autowired
    CoffeShopRepository coffeShopRepository;

    public List<CardTemplate> getCardTemplatesFromCoffeShop(String coffeShopId) {
        Optional<CoffeeShop> coffeeShopOptional = coffeShopRepository.findById(coffeShopId);
        return coffeeShopOptional.map(CoffeeShop::getCardTemplates).orElse(null);
    }

    public CardTemplate addCardTemplateToCoffeShop(String coffeShopId, CardTemplate cardTemplate) {
        Optional<CoffeeShop> coffeeShopOptional = coffeShopRepository.findById(coffeShopId);
        if (coffeeShopOptional.isPresent()){
            CardTemplate savedCardTemplate = cardTemplateRepository.save(cardTemplate);
            CoffeeShop coffeeShop = coffeeShopOptional.get();

            List<CardTemplate> templates = coffeeShop.getCardTemplates();
            templates.add(savedCardTemplate);

            coffeShopRepository.save(coffeeShop);
            return savedCardTemplate;
        } else {
            return null;
        }
    }

    public CardTemplate editCardTemplate(String cardTemplateId, CardTemplate cardTemplate) {
        Document document = new Document();
        mongoOperations.getConverter().write(cardTemplate, document);
        Update update = new Update();
        document.forEach(update::set);

        return mongoOperations.findAndModify(
                Query.query(Criteria.where("id").is(cardTemplateId)),
                update,
                CardTemplate.class);
    }

    public CardTemplate deleteCardTemplate(String coffeShopId, String cardTemplateId) {
        Optional<CoffeeShop> coffeeShopOptional = coffeShopRepository.findById(coffeShopId);
        if (coffeeShopOptional.isPresent()){
            CoffeeShop coffeeShop = coffeeShopOptional.get();
            CardTemplate removedCardTemplate = cardTemplateRepository.removeById(cardTemplateId);
            List<CardTemplate> templates = coffeeShop.getCardTemplates();
            coffeeShop.setCardTemplates(templates.stream().filter(cardTemplate -> !cardTemplate.getId().equals(cardTemplateId)).collect(Collectors.toList()));
            coffeShopRepository.save(coffeeShop);
            return removedCardTemplate;
        } else {
            return null;
        }
    }
}
