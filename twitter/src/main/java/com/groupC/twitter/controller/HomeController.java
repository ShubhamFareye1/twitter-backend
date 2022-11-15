package com.groupC.twitter.controller;

import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "This is Twitter Home Page";
    }
}
