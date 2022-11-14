package com.groupC.twitter.controller;

import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class tweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping("/tweets")
    public ResponseEntity getTweets(){
        return new ResponseEntity(tweetService.getTweets(), HttpStatus.OK);
    }

    @GetMapping("/tweets/{id}")
    public ResponseEntity getTweet(@PathVariable("id") Long id){
        return new ResponseEntity(tweetService.getTweetById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/tweets")
    public ResponseEntity getTweets(@PathVariable("id") Long id){
        return new ResponseEntity(tweetService.getTweetsByUser(id), HttpStatus.OK);
    }

    @PostMapping("/tweets")
    public ResponseEntity createTweet(@RequestBody TweetDto tweetDto){
        return new ResponseEntity(tweetService.addTweet(tweetDto),HttpStatus.CREATED);
    }

    @PostMapping("/{id}/tweets")
    public ResponseEntity createTweet(@RequestBody TweetDto tweetDto,@PathVariable("id") Long id){
        return new ResponseEntity(tweetService.addTweet(tweetDto,id),HttpStatus.CREATED);
    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity deleteTweet(@PathVariable("id") Long id){
        tweetService.deleteTweet(id);
        return new ResponseEntity<>("success deleted",HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tweets/search/{keyword}")
    public ResponseEntity searchTweets(@PathVariable("keyword") String keyword){
        return new ResponseEntity(tweetService.searchTweets(keyword),HttpStatus.OK);
    }

    @PostMapping("/{userId}/tweets/{tweetId}")
    public ResponseEntity addLike(@PathVariable("userId") Long userId,@PathVariable("tweetId") Long tweetId){
        return new ResponseEntity(tweetService.addLike(tweetId,userId),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/tweets/{tweetId}")
    public ResponseEntity removeLike(@PathVariable("userId") Long userId,@PathVariable("tweetId") Long tweetId){
        return new ResponseEntity(tweetService.removeLike(tweetId,userId),HttpStatus.OK);
    }

}
