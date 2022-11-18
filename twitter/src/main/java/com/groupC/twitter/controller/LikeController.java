package com.groupC.twitter.controller;

import com.groupC.twitter.service.LikeService;
import com.groupC.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/{userId}/tweets/{tweetId}")
    public ResponseEntity getLike(@PathVariable("userId") Long userId, @PathVariable("tweetId") Long tweetId){
        return new ResponseEntity(likeService.getLike(tweetId,userId), HttpStatus.OK);
    }

    @GetMapping("/tweetLike/{userId}")
    public ResponseEntity getTweetLike(@PathVariable("userId") long userId){
        return new ResponseEntity(likeService.getTweetLike(userId),HttpStatus.OK);
    }

    @PostMapping("/{userId}/tweets/{tweetId}")
    public ResponseEntity addLike(@PathVariable("userId") Long userId,@PathVariable("tweetId") Long tweetId){
        return new ResponseEntity(likeService.addLike(tweetId,userId),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/tweets/{tweetId}")
    public ResponseEntity removeLike(@PathVariable("userId") Long userId,@PathVariable("tweetId") Long tweetId){
        return new ResponseEntity(likeService.removeLike(tweetId,userId),HttpStatus.OK);
    }
}
