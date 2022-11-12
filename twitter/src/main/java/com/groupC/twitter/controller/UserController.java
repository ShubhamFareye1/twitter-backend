package com.groupC.twitter.controller;

import com.groupC.twitter.model.Messages;
import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public void signup(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping("")
    public void updateUser(@RequestBody User user){
        userService.updateUserData(user);
    }

    @GetMapping("/{userId}")
    public User userDetails(@PathVariable("userId") long userId){
        return userService.getUser(userId);
    }

//    @GetMapping("")
//    public List<Messages> getMessage(@PathVariable long userId,@RequestParam long senderId){
//        return userService.getMessage(userId,senderId);
//    }

    @GetMapping("")
    public void deleteUser(@PathVariable long userId){
        userService.deleteUser(userId);
    }





}
