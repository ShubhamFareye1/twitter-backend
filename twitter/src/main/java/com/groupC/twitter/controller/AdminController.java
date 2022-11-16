package com.groupC.twitter.controller;

import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public class AdminController {

    @Autowired
    private UserService userService;

    // admin can see all blue tick request using this api
    @GetMapping("/admin/bluetick")
    public ResponseEntity bluetickRequest(){
        return new ResponseEntity(userService.getRequestBluetick(), HttpStatus.OK);
    }

    // admin can update the status of user bluetick request.
    @PutMapping("/admin/bluetick/status/{userId}/{response}")
    public ResponseEntity bluetick(@PathVariable("userId") long userId, @PathVariable("response") boolean resp){
        return new ResponseEntity(userService.setBluetick(userId,resp),HttpStatus.OK);
    }
}
