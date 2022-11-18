package com.groupC.twitter.controller;

import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.model.User;
import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    public UserService userService;

    @GetMapping("")
    public  String  homePage(){
        return "Welcome to SASTA Twitter";
    }

//    @GetMapping("")
//    public ResponseEntity userDetails() {
//        return new ResponseEntity(userService.getUser(3), HttpStatus.OK);
//    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserDto user){
        return new ResponseEntity(userService.addUser(user), HttpStatus.OK);
    }
}
