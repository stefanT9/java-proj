package com.example.proiect_java.controller;

import com.example.proiect_java.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.proiect_java.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> getAllUsers(){
        try{
            return userService.getAllUsers();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping()
    public User postUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User putUser(@RequestBody User user,@PathVariable String id){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public User putUser(@PathVariable String id){
        return userService.removeUser(id);
    }
}
