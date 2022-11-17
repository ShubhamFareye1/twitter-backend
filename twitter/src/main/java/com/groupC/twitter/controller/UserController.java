package com.groupC.twitter.controller;

import com.groupC.twitter.dto.BookmarkDto;
import com.groupC.twitter.dto.MessagesDto;
import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.service.NotificationService;
import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    // this for update user in database.
    @PutMapping("")
    public ResponseEntity updateUser(@RequestBody UserDto userDto){
       return new ResponseEntity(userService.updateUser(userDto),HttpStatus.OK);
    }

    //this api is for get user by userID.
    @GetMapping("/{userId}")
    public ResponseEntity userDetails(@PathVariable("userId") long userId){
        return new ResponseEntity(userService.getUser(userId),HttpStatus.OK);
    }

    //this api is use for get all user.
    @GetMapping("")
    public ResponseEntity allUsers(){
        return new ResponseEntity(userService.getAllUsers(),HttpStatus.OK);
    }

    //this api is use for get user by username.
    @GetMapping("/username/{userName}")
    public ResponseEntity getUserByUsername(@PathVariable("userName") String userName){
        return new ResponseEntity(userService.getUserByUserName(userName),HttpStatus.OK);
    }

    //this api is use for delete user by userID
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") long userId){
        userService.deleteUser(userId);
        return new ResponseEntity("User Deleted Success Fully",HttpStatus.OK);
    }

    //this api is use for add follower of user
    @PutMapping("/follow/{userId}/{followerId}")
    public ResponseEntity addFollower(@PathVariable("userId") long userId, @PathVariable("followerId") long followerId) {
        return new ResponseEntity(userService.addFollower(followerId,userId),HttpStatus.OK);
    }

    // this api is use for remove follower of user.
    @DeleteMapping("/follow/{userId}/{followerId}")
    public ResponseEntity removeFollower(
            @PathVariable("userId") long userId, @PathVariable("followerId") long followerId) {
        return new ResponseEntity(userService.removeFollower(followerId,userId),HttpStatus.OK);
    }

    //this api is use for delete following of user and also delete follower of followed user.
    @DeleteMapping("/following/{userId}/{followingId}")
    public ResponseEntity removeFollowing(@PathVariable("userId") long userId,@PathVariable("followingId") long followingId){
        return new ResponseEntity(userService.deleteFollowing(userId,followingId),HttpStatus.OK);
    }

    //this api is used for get list of follower of user.
    @GetMapping("/{userId}/followers")
    public ResponseEntity getFollowers(@PathVariable("userId") long userId) {
        return new ResponseEntity(userService.getFollowers(userId),HttpStatus.OK);
    }

    //this api is used for get following list of user.
    @GetMapping("/{userId}/followings")
    public ResponseEntity getFollowings(@PathVariable("userId") long userId) {
        return new ResponseEntity(userService.getFollowings(userId),HttpStatus.OK);
    }

    @PostMapping("/following/{userId}/{followingId}")
    public ResponseEntity addFollowing(@PathVariable("userId") long userId,@PathVariable("followingId") long followingId){
        return new ResponseEntity(userService.addFollowing(userId,followingId),HttpStatus.OK);
    }

    //this api is use for request blue tick for user.
    @PutMapping("/bluetick/{userId}")
    public ResponseEntity requestBluetick(@PathVariable("userId") long userId){
        userService.requestBluetick(userId);
        return new ResponseEntity("Blue tick requested",HttpStatus.OK);
    }

    //this api is use for getting all the notification of user.
    @GetMapping("/notification/{userId}")
    public ResponseEntity notification(@PathVariable("userId") long userId){
        return new ResponseEntity(notificationService.getNotification(userId),HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity searchUser(@PathVariable("keyword") String user){
        return new ResponseEntity<>(userService.searchUser(user),HttpStatus.OK);
    }

    @GetMapping("/trending")
    public ResponseEntity topUser(){
        return new ResponseEntity(userService.topTenUser(),HttpStatus.OK);
    }

}
