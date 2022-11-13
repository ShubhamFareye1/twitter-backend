package com.groupC.twitter.controller;

import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.model.Messages;
import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public ResponseEntity signup(@RequestBody UserDto user){
        return new ResponseEntity(userService.addUser(user), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity updateUser(@RequestBody UserDto userDto){
       return new ResponseEntity(userService.updateUser(userDto),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity userDetails(@PathVariable("userId") long userId){
        return new ResponseEntity(userService.getUser(userId),HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity getUserByUserName(@PathVariable("userName") String userName){
        return new ResponseEntity(userService.getUserByUserName(userName),HttpStatus.OK);
    }

    @DeleteMapping("")
    public void deleteUser(@PathVariable long userId){
        userService.deleteUser(userId);
    }

//    @PutMapping("/{userId}/follow")
//    public ResponseEntity<HttpStatus> addFollower(@PathVariable long userId, Principal principal) {
//        userService.addFollower(userId, UUID.randomUUID()); // TODO: Extract from Principal
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{userId}/follow")
//    public ResponseEntity<HttpStatus> removeFollower(
//            @PathVariable("userId") UUID userId, Principal principal) {
//        userService.removeFollower(userId, UUID.randomUUID()); // TODO: Extract from Principal
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity getFollowers(@PathVariable("userId") long userId) {
        return new ResponseEntity(userService.getFollowers(userId),HttpStatus.OK);
    }

    @GetMapping("/{userId}/followings")
    public ResponseEntity getFollowings(@PathVariable("userId") long userId) {
        return new ResponseEntity(userService.getFollowings(userId),HttpStatus.OK);
    }

}
