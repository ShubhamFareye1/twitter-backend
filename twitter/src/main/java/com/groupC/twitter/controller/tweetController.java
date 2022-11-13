package com.groupC.twitter.controller;

import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class tweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping("user/tweets")
    public ResponseEntity getTweets(){
        return new ResponseEntity(tweetService.getTweets(), HttpStatus.OK);
    }

    @GetMapping("user/tweets/{id}")
    public ResponseEntity getTweet(@PathVariable("id") Long id){
        return new ResponseEntity(tweetService.getTweetById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/tweets")
    public ResponseEntity getTweets(@PathVariable("id") Long id){
        return new ResponseEntity(tweetService.getTweetsByUser(id), HttpStatus.OK);
    }

    @PostMapping("user/tweets")
    public ResponseEntity createTweet(@RequestBody TweetDto tweetDto){
        return new ResponseEntity(tweetService.addTweet(tweetDto),HttpStatus.CREATED);
    }

    @PostMapping("user/{id}/tweets")
    public ResponseEntity createTweet(@RequestBody TweetDto tweetDto,@PathVariable("id") Long id){
        return new ResponseEntity(tweetService.addTweet(tweetDto,id),HttpStatus.CREATED);
    }

    @DeleteMapping("user/tweets/{id}")
    public ResponseEntity deleteTweet(@PathVariable("id") Long id){
        tweetService.deleteTweet(id);
        return new ResponseEntity<>("success deleted",HttpStatus.NO_CONTENT);
    }

}
