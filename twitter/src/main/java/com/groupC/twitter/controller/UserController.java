package com.groupC.twitter.controller;

import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;

@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public void signup(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping("/{userId}")
    public User userDetails(@PathVariable("userId") long userId){
        return userService.getUser(userId);
    }


}
