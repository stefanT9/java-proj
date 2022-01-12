package com.example.proiect_java.service;

import com.example.proiect_java.entity.Card;
import com.example.proiect_java.entity.User;
import com.example.proiect_java.repository.CardRepository;
import com.example.proiect_java.repository.UserRepository;
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
public class CardService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    MongoOperations mongoOperations;

    public List<Card> getCardsOfUser(String userId) {
        User user = userRepository.findUserById(userId);
        return user.getCards().stream().map(card -> {
            Optional<Card> optionalCard = cardRepository.findById(card.getId());
            return optionalCard.orElse(null);
        }).collect(Collectors.toList());
    }

    public Card addCardToUser(String userId, Card card) {
        Card savedCard = cardRepository.save(card);
        User user = userRepository.findUserById(userId);
        List<Card> cards = user.getCards();
        cards.add(savedCard);
        userRepository.save(user);
        return savedCard;
    }

    public Card editCard(String userId, String cardId, Card card) {
        Document document = new Document();
        mongoOperations.getConverter().write(card, document);
        Update update = new Update();
        document.forEach(update::set);

        return mongoOperations.findAndModify(
                Query.query(Criteria.where("id").is(cardId)),
                update,
                Card.class);
    }

    public Card removeCard(String userId, String cardId) {
        Card removedCard = cardRepository.removeById(cardId);

        User user = userRepository.findUserById(userId);
        List<Card> cards = user.getCards();
        user.setCards(cards.stream().filter( card -> !card.getId().equals(cardId)).collect(Collectors.toList()));
        userRepository.save(user);

        return removedCard;
    }
}
