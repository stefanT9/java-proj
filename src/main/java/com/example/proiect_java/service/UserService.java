package com.example.proiect_java.service;

import com.example.proiect_java.entity.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.example.proiect_java.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MongoOperations mongoOperations;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        System.out.println(user);
        return userRepository.save(user);
    }

    public User updateUser(String id, User user){
        Document document = new Document();
        mongoOperations.getConverter().write(user, document);
        Update update = new Update();
        document.forEach(update::set);

        return mongoOperations.findAndModify(
                Query.query(Criteria.where("id").is(id)),
                update,
                User.class);
    }

    public User removeUser(String id) {
        return userRepository.removeById(id);
    }
}
