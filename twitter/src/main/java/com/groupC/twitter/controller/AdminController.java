package com.groupC.twitter.controller;

import com.groupC.twitter.service.AdminService;
import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    // admin can see all blue tick request using this api
    @GetMapping("/bluetick")
    public ResponseEntity bluetickRequest(){
        return new ResponseEntity(adminService.getRequestBluetick(), HttpStatus.OK);
    }

    // admin can update the status of user bluetick request.
    @PutMapping("/bluetick/status/{userId}/{response}")
    public ResponseEntity bluetick(@PathVariable("userId") long userId, @PathVariable("response") boolean resp){
        return new ResponseEntity(adminService.setBluetick(userId,resp),HttpStatus.OK);
    }
}
