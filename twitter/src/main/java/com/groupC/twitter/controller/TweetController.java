package com.groupC.twitter.controller;

import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping("/tweets")
    public ResponseEntity getTweets(){
        return new ResponseEntity(tweetService.getTweets(), HttpStatus.OK);
    }

    @GetMapping("/tweets/{tweetId}")
    public ResponseEntity getTweet(@PathVariable("tweetId") Long id){
        return new ResponseEntity(tweetService.getTweetById(id), HttpStatus.OK);
    }

    @GetMapping("/{userId}/tweets")
    public ResponseEntity getTweets(@PathVariable("userId") Long id){
        return new ResponseEntity(tweetService.getTweetsByUser(id), HttpStatus.OK);
    }

    @GetMapping("/{userId}/feeds")
    public ResponseEntity getFeeds(@PathVariable("userId") Long id){
        return new ResponseEntity(tweetService.getFeeds(id),HttpStatus.OK);
    }

    @PostMapping("/tweets")
    public ResponseEntity createTweet(@RequestBody TweetDto tweetDto){
        return new ResponseEntity(tweetService.addTweet(tweetDto),HttpStatus.CREATED);
    }

    @PostMapping("{userId}/retweets/{tweetId}")
    public ResponseEntity retweet(@PathVariable("userId") Long userId,@PathVariable("tweetId") Long tweetId){
        return new ResponseEntity(tweetService.reTweet(userId,tweetId),HttpStatus.OK);
    }

    @PostMapping("/{userId}/tweets")
    public ResponseEntity createTweet(@RequestBody TweetDto tweetDto,@PathVariable("userId") Long id){
        return new ResponseEntity(tweetService.addTweet(tweetDto,id),HttpStatus.CREATED);
    }

    @DeleteMapping("/tweets/{tweetId}")
    public ResponseEntity deleteTweet(@PathVariable("tweetId") Long id){
        tweetService.deleteTweet(id);
        return new ResponseEntity<>("success deleted",HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tweets/search/{keyword}")
    public ResponseEntity searchTweets(@PathVariable("keyword") String keyword){
        return new ResponseEntity(tweetService.searchTweets(keyword),HttpStatus.OK);
    }

}
