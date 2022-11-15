package com.groupC.twitter.controller;

import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.model.User;
import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    public UserService userService;
    @GetMapping("/")
    public List<UserDto> homePage(){
        return userService.getAllUsers();
    }
}
