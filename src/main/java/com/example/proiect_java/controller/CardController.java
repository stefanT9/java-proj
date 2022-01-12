package com.example.proiect_java.controller;

import com.example.proiect_java.entity.Card;
import com.example.proiect_java.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/card")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping()
    public List<Card> getCardsOfUser(@PathVariable String userId){
        return cardService.getCardsOfUser(userId);
    }

    @PostMapping()
    public Card addCardToUser(@PathVariable String userId,@RequestBody Card card){
        return cardService.addCardToUser(userId, card);
    }

    @PutMapping("/{cardId}")
    public Card editCard(@PathVariable String userId, @PathVariable String cardId, @RequestBody Card card){
        return cardService.editCard(userId, cardId, card);
    }

    @DeleteMapping("/{cardId}")
    public Card deleteCard(@PathVariable String userId, @PathVariable String cardId){
        return cardService.removeCard(userId, cardId);
    }
}
