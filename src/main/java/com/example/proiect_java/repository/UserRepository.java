package com.example.proiect_java.repository;


import com.example.proiect_java.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public List<User> findAll();
    public User findUserById(String id);
    public List<User> findUsersById(String id);
    public User removeById(String id);
}
